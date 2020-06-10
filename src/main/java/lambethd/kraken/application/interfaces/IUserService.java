package lambethd.kraken.application.interfaces;

import domain.User;
import lambethd.kraken.application.exception.UserRegistrationException;

public interface IUserService {
    User registerUser(User user) throws UserRegistrationException;
    User getUserByUsername(String username);
}
