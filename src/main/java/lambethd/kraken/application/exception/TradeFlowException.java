package lambethd.kraken.application.exception;

import org.springframework.http.HttpStatus;

public class TradeFlowException extends HttpException {
    public TradeFlowException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
