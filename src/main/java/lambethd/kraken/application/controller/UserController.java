package lambethd.kraken.application.controller;

import domain.User;
import dto.UserDto;
import lambethd.kraken.application.exception.UnauthorizedException;
import lambethd.kraken.application.exception.UserRegistrationException;
import lambethd.kraken.application.interfaces.IAuthService;
import lambethd.kraken.application.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws UserRegistrationException {
        return buildResponseEntity(userService.registerUser(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserDto> authenticateUser(@RequestBody User user) throws UnauthorizedException, InvalidKeySpecException, IOException, NoSuchAlgorithmException, NoSuchProviderException, URISyntaxException {
        return buildResponseEntity(authService.authenticateUser(user));
    }
}
