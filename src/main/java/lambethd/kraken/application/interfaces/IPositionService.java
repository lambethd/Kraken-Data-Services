package lambethd.kraken.application.interfaces;

import portfolio.Position;
import portfolio.Trade;

import java.util.List;

public interface IPositionService {
    void applyToPosition(Trade current, Trade previous);
    Position createPosition(Trade current);
    List<Position> getAllPositions(String username);
}
