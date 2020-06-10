package lambethd.kraken.application.exception;

import org.springframework.http.HttpStatus;

public class UnableToMapException extends HttpException {
    public UnableToMapException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }
}
