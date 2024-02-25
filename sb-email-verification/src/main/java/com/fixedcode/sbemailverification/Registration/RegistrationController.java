package com.fixedcode.sbemailverification.Registration;

import com.fixedcode.sbemailverification.Event.Listener.RegistrationCompleteEvent;
import com.fixedcode.sbemailverification.Event.Listener.RegistrationCompleteEventListener;
import com.fixedcode.sbemailverification.Registration.Password.PasswordRequestUtil;
import com.fixedcode.sbemailverification.Registration.Password.PasswordResetToken;
import com.fixedcode.sbemailverification.Registration.Token.VerificationToken;
import com.fixedcode.sbemailverification.Registration.Token.VerificationTokenRepository;
import com.fixedcode.sbemailverification.User.User;
import com.fixedcode.sbemailverification.User.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping ("/register")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final RegistrationCompleteEventListener eventListener;
    private final VerificationTokenRepository tokenRepository;
    private final HttpServletRequest servletRequest;

    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
        User user = userService.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Success!  Please, check your email for to complete your registration";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token, HttpServletRequest request){
        String url = applicationUrl(servletRequest) + "/register/resend-verification-token?token=" + token;
        VerificationToken theToken = tokenRepository.findByToken(token);
        if (theToken.getUser().isEnabled()){
            return "This account has already been verified, please, login.";
        }
        log.info("before");
        String verificationResult = userService.validateToken(token);
        log.info("after");
        if (verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully. Now you can login to your account";
        }
        return "Invalid verification token. <a href=\""+ url + "\">Get a new verification link </a>";
    }

    @GetMapping("/resend-verification-token")
    public String resendVerificationToken(@RequestParam("token") String token, final HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        VerificationToken newToken = userService.generateNewVerificationToken(token);
        var theUser = newToken.getUser();
        resendVerificationTokenEmail(theUser, newToken, applicationUrl(request));
        return "A new verification link has been sent to your mail, " +
                "please complete your registration";
    }

    private void resendVerificationTokenEmail(User user,VerificationToken verificationToken, String applicationUrl) throws MessagingException, UnsupportedEncodingException {
        String url = applicationUrl+"/register/verifyEmail?token="+verificationToken.getToken();
        eventListener.sendVerificationEmail(url);
        log.info("Click the link to verify registration: {}", url);
    }

    @PostMapping("/password-reset-request")
    public String resetPasswordRequest(@RequestBody PasswordRequestUtil requestUtil,
                                       final HttpServletRequest servletRequest) throws MessagingException, UnsupportedEncodingException {
        Optional<User> user = userService.findByEmail(requestUtil.getEmail());
        String resetUrl = "";
        if(user.isPresent())
        {
            String passwordResetToken = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user.get(), passwordResetToken);
            resetUrl = passwordResetEmailLink(user.get(), applicationUrl(servletRequest), passwordResetToken);
        }
        return resetUrl;
    }

    private String passwordResetEmailLink(User user, String applicationUrl, String passwordResetToken) throws MessagingException, UnsupportedEncodingException {
        String url =  applicationUrl + "/register/reset-password?token="+passwordResetToken;
        eventListener.sendPasswordResetVerificationEmail(url);
        log.info("Click the link to reset your password :  {}", url);
        return url;
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody PasswordRequestUtil passwordRequestUtil,
                                @RequestParam("token") String token){
        String tokenVerificationResult = userService.validatePasswordResetToken(token);
        if (!tokenVerificationResult.equalsIgnoreCase("valid")) {
            return "Invalid token password reset token";
        }
        Optional<User> theUser = Optional.ofNullable(userService.findUserByPasswordToken(token));
        if (theUser.isPresent()) {
            userService.changePassword(theUser.get(), passwordRequestUtil.getNewPassword());
            return "Password has been reset successfully";
        }
        return "Invalid password reset token";
    }
    @PostMapping("/change-password")
    public String changePassword(@RequestBody PasswordRequestUtil requestUtil){
        User user = userService.findByEmail(requestUtil.getEmail()).get();
        if (!userService.oldPasswordIsValid(user, requestUtil.getOldPassword())){
            return "Incorrect old password";
        }
        userService.changePassword(user, requestUtil.getNewPassword());
        return "Password changed successfully";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
