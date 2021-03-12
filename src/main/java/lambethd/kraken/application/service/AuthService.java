package lambethd.kraken.application.service;

import domain.User;
import dto.UserDto;
import io.jsonwebtoken.*;
import lambethd.kraken.application.exception.UnauthorizedException;
import lambethd.kraken.application.interfaces.IAuthService;
import lambethd.kraken.application.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private IUserService userService;
    @Value("${public_key_file_location}")
    private String publicKeyFile;
    @Value("${private_key_file_location}")
    private String privateKeyFile;
    @Value("${jwt_expiration_addon}")
    private long expirationAddon;

    @Override
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((User) authentication.getPrincipal()).getUsername();
    }

    @Override
    public UserDto authenticateUser(User user) throws UnauthorizedException, NoSuchAlgorithmException, URISyntaxException, NoSuchProviderException, InvalidKeySpecException, IOException {
        User sourceUser = userService.getUserByUsername(user.getUsername());
        if (sourceUser != null && sourceUser.getUsername().equalsIgnoreCase(user.getUsername()) && sourceUser.getPassword().equals(user.getPassword())) {
            UserDto userDto = new UserDto();
            userDto.setUsername(sourceUser.getUsername());
            userDto.setFirstName(sourceUser.getFirstName());
            userDto.setLastName(sourceUser.getLastName());
            userDto.setScope(sourceUser.getScope().toString());
            userDto.setToken(generateJwt(userDto));
            return userDto;
        } else {
            throw new UnauthorizedException("User " + user.getUsername() + " could not be authenticated");
        }
    }

    @Override
    public RSAPublicKey getPublicKey() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        String publicKeyContent = new String(Files.readAllBytes(Paths.get(publicKeyFile)));

        publicKeyContent = publicKeyContent.replaceAll("\\r", "").replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");
        ;

        KeyFactory kf = KeyFactory.getInstance("RSA");

        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        return (RSAPublicKey) kf.generatePublic(keySpecX509);
    }

    @Override
    public PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String privateKeyContent = new String(Files.readAllBytes(Paths.get(privateKeyFile)));

        privateKeyContent = privateKeyContent.replaceAll("\\n", "").replace("\r","").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");

        KeyFactory kf = KeyFactory.getInstance("RSA");

        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        return kf.generatePrivate(keySpecPKCS8);
    }

    public String generateJwt(UserDto user) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        PrivateKey privateKey = getPrivateKey();
        return Jwts.builder()
                .setIssuer("Kraken-Auth")
                .setSubject(user.getUsername())
                .claim("name", user.getFirstName() + " " + user.getLastName())
                .claim("scope", user.getScope())
                .setIssuedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(expirationAddon).toInstant(ZoneOffset.UTC)))
                .signWith(
                        SignatureAlgorithm.RS256,
                        privateKey
                )
                .compact();
    }

    private Jws<Claims> getClaims(String token) throws UnauthorizedException {
        try {
            PublicKey publicKey = getPublicKey();
            return Jwts.parser().setSigningKey(publicKey)
                    .parseClaimsJws(token);
        } catch (InvalidKeySpecException | ExpiredJwtException | NoSuchAlgorithmException | IOException e) {
            throw new UnauthorizedException("Unable to getClaims from token: " + token, e);
        }
    }

    @Override
    public Boolean validate(String token) throws UnauthorizedException {
        Jws<Claims> claims = getClaims(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    @Override
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    public Authentication getAuthentication(String token) throws UnauthorizedException {
        User userDetails = userService.getUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", getAuthorities(userDetails));
    }

    public String getUsername(String token) throws UnauthorizedException {
        return getClaims(token).getBody().getSubject();
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        if (user != null && user.getScope() != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority(user.getScope().toString());
            Collection<GrantedAuthority> collection = new HashSet<>();
            collection.add(authority);
            return collection;
        } else {
            return new HashSet<>();
        }
    }
}
