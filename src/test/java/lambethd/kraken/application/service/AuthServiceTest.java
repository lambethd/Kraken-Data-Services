package lambethd.kraken.application.service;

import domain.AuthScope;
import dto.UserDto;
import lambethd.kraken.application.Configuration;
import lambethd.kraken.application.WebSecurityConfig;
import lambethd.kraken.application.exception.UnauthorizedException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class, WebSecurityConfig.class})
@Ignore
public class AuthServiceTest {

    @InjectMocks
    private AuthService underTest;

    @Test
    public void authenticateUser() {
    }

    @Test
    public void generateJwt() throws NoSuchProviderException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, URISyntaxException, UnauthorizedException {
        UserDto user = new UserDto();
        user.setFirstName("David");
        user.setLastName("Lambeth");
        user.setUsername("devilchat");
        user.setScope(AuthScope.USER.toString());

        String token = underTest.generateJwt(user);
        System.out.println(token);

        Assert.assertTrue(underTest.validate(token));
    }

    @Test
    public void generateJwt_Fails() throws NoSuchProviderException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, URISyntaxException, UnauthorizedException {
        UserDto user = new UserDto();
        user.setFirstName("David");
        user.setLastName("Lambeth");
        user.setUsername("devilchat");
        user.setScope(AuthScope.USER.toString());

        String token = underTest.generateJwt(user);
        System.out.println(token);

        Boolean validated = underTest.validate(token);

        Assert.assertEquals(true, validated);
    }
}