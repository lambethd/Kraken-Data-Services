package lambethd.kraken.application.controller;

import lambethd.kraken.application.interfaces.IAuthService;
import lambethd.kraken.application.interfaces.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.Position;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController extends BaseController {

    @Autowired
    private IAuthService authService;
    @Autowired
    private IPositionService positionService;

    @GetMapping
    public ResponseEntity<List<Position>> getPositions() {
        return buildResponseEntity(positionService.getAllPositions(authService.getCurrentUser()));
    }
}
