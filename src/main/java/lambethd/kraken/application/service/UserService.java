package lambethd.kraken.application.service;

import domain.AuthScope;
import domain.User;
import lambethd.kraken.application.exception.UserRegistrationException;
import lambethd.kraken.application.interfaces.IUserService;
import lambethd.kraken.application.interfaces.IUserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserValidationService userValidationService;

    @Override
    public User registerUser(User user) throws UserRegistrationException {
        user.setScope(AuthScope.USER);
        userValidationService.validateUser(user);

        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
