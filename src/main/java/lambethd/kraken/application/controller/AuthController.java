package lambethd.kraken.application.controller;

import lambethd.kraken.application.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/authentication/details")
public class AuthController extends BaseController {

    @Autowired
    private IAuthService authService;

    @GetMapping
    public ResponseEntity<String> getPublicKey() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        return buildResponseEntity(authService.getPublicKey().toString());
    }
}
