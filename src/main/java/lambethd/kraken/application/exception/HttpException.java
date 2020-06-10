package lambethd.kraken.application.exception;

import org.springframework.http.HttpStatus;

public class HttpException extends Exception {
    private HttpStatus httpStatusCode;

    public HttpException(HttpStatus httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public HttpException(HttpStatus httpStatusCode, String message, Exception inner){
        super(message, inner);
        this.httpStatusCode = httpStatusCode;
    }

    public HttpException(HttpStatus httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatus httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
