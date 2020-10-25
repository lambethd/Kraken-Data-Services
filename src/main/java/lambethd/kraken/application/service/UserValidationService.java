package lambethd.kraken.application.service;

import domain.User;
import lambethd.kraken.application.exception.UserRegistrationException;
import lambethd.kraken.application.interfaces.IUserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserValidationService implements IUserValidationService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public void validateUser(User user) throws UserRegistrationException {
        List<User> users = userRepository.findAll();
        if(users.stream().anyMatch((user1 -> user1.getUsername().equalsIgnoreCase(user.getUsername())))){
            throw new UserRegistrationException("Username " + user.getUsername() + " already taken.");
        }
        //TODO: add more validation here
    }
}
