package lambethd.kraken.application.controller;

import lambethd.kraken.application.interfaces.IAuthService;
import lambethd.kraken.application.interfaces.ISpreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.Spread;

import java.util.List;

@RestController
@RequestMapping("/spread")
public class SpreadController extends BaseController {

    @Autowired
    private IAuthService authService;
    @Autowired
    private ISpreadService spreadService;

    @GetMapping
    public ResponseEntity<List<Spread>> getSpreads() {
        return buildResponseEntity(spreadService.getSpreads(authService.getCurrentUser()));
    }

    @PostMapping
    public ResponseEntity<Spread> createSpread(@RequestBody Spread spread) {
        return buildResponseEntity(spreadService.createSpread(spread));
    }
}
