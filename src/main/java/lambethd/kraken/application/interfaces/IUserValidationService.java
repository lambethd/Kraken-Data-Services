package lambethd.kraken.application.interfaces;

import domain.User;
import lambethd.kraken.application.exception.UserRegistrationException;

public interface IUserValidationService {

    void validateUser(User user) throws UserRegistrationException;
}
