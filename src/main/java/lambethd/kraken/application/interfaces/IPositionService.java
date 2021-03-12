package lambethd.kraken.application.interfaces;

import lambethd.kraken.application.exception.EntityNotFoundException;
import lambethd.kraken.application.exception.UnauthorizedException;
import portfolio.Position;
import portfolio.Trade;

import java.util.List;

public interface IPositionService {
    void applyToPosition(Trade current, Trade previous);
    Position createPosition(Trade current);
    List<Position> getAllPositions(String username);
    Position updatePosition(Position position, String username) throws EntityNotFoundException, UnauthorizedException;
    void deletePosition(String positionId, String username) throws EntityNotFoundException, UnauthorizedException;
}
