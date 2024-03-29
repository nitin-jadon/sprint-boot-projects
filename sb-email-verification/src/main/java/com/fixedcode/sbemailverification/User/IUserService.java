package com.fixedcode.sbemailverification.User;

import com.fixedcode.sbemailverification.Registration.RegistrationRequest;
import com.fixedcode.sbemailverification.Registration.Token.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getUsers();
    User registerUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);
    void saveUserVerificationToken(User theUser, String token);
    String validateToken(String theToken);
    VerificationToken generateNewVerificationToken(String token);
    void changePassword(User theUser, String newPassword);
    String validatePasswordResetToken(String token);
    User findUserByPasswordToken(String token);
    void createPasswordResetTokenForUser(User user, String passwordResetToken);
    boolean oldPasswordIsValid(User user, String oldPassword);
}
