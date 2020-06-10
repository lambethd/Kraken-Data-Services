package lambethd.kraken.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected <T> ResponseEntity<T> buildResponseEntity(T user) {
        return new ResponseEntity<T>(user, HttpStatus.OK);
    }
}
