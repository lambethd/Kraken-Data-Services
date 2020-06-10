package lambethd.kraken.application.interfaces;

import domain.User;
import dto.UserDto;
import lambethd.kraken.application.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

public interface IAuthService {
    String getCurrentUser();
    UserDto authenticateUser(User user) throws UnauthorizedException, NoSuchAlgorithmException, URISyntaxException, NoSuchProviderException, InvalidKeySpecException, IOException;
    RSAPublicKey getPublicKey() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException;
    PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException;
    String generateJwt(UserDto user) throws IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, URISyntaxException;
    Boolean validate(String token) throws UnauthorizedException;
    String resolveToken(HttpServletRequest request);
    Authentication getAuthentication(String token) throws UnauthorizedException;
}
