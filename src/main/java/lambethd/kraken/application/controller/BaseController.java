package lambethd.kraken.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:4200", "http://ec2-3-21-53-5.us-east-2.compute.amazonaws.com", "https://krakenscove.co.uk", "http://krakenscove.co.uk"})
public class BaseController {

    protected <T> ResponseEntity<T> buildResponseEntity(T document) {
        return new ResponseEntity<T>(document, HttpStatus.OK);
    }
}
