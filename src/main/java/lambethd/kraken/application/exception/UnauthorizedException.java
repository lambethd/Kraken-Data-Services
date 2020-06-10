package lambethd.kraken.application.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HttpException {
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
    public UnauthorizedException(String message, Exception inner){
        super(HttpStatus.UNAUTHORIZED, message, inner);
    }
}
