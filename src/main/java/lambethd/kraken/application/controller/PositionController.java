package lambethd.kraken.application.controller;

import lambethd.kraken.application.exception.EntityNotFoundException;
import lambethd.kraken.application.exception.UnauthorizedException;
import lambethd.kraken.application.interfaces.IAuthService;
import lambethd.kraken.application.interfaces.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public ResponseEntity<Position> updatePosition(@RequestBody Position position) throws UnauthorizedException, EntityNotFoundException {
        return buildResponseEntity(positionService.updatePosition(position, authService.getCurrentUser()));
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable String id) throws UnauthorizedException, EntityNotFoundException {
        positionService.deletePosition(id, authService.getCurrentUser());
    }
}
