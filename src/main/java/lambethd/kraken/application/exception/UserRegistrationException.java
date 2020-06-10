package lambethd.kraken.application.exception;

import org.springframework.http.HttpStatus;

public class UserRegistrationException extends HttpException {
    public UserRegistrationException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }
}
