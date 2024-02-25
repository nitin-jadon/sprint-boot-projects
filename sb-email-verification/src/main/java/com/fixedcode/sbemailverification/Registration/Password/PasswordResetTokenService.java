package com.fixedcode.sbemailverification.Registration.Password;

import com.fixedcode.sbemailverification.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    public void createPasswordResetTokenForUser(User user, String token)
    {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }
        public String validatePasswordResetToken(String token)
        {
            PasswordResetToken validatetoken = passwordResetTokenRepository.findByToken(token);
            if(validatetoken == null)
            {
                return "Invalid verification token";
            }
            User user = validatetoken.getUser();
            Calendar calendar = Calendar.getInstance();
            if(calendar.getTime().getTime() - validatetoken.getExpirationTime().getTime() <= 0)
            {
                return "link expired, reset link";
            }
            return "valid";
        }
        public Optional<User> findUserByPasswordToken(String token)
        {
            return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
        }

        public PasswordResetToken findPasswordResetToken(String token)
        {
            return passwordResetTokenRepository.findByToken(token);
        }
}
