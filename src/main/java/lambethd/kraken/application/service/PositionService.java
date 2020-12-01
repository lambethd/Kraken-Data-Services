package lambethd.kraken.application.service;

import lambethd.kraken.application.interfaces.IPositionService;
import lambethd.kraken.data.mongo.repository.IPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.BuySell;
import portfolio.Position;
import portfolio.Trade;

import java.util.List;

@Service
public class PositionService implements IPositionService {
    @Autowired
    private IPositionRepository positionRepository;

    @Override
    public void applyToPosition(Trade current, Trade previous) {
        Position position = positionRepository.getPositionByItemIdAndUsername(current.getItemId(), current.getUsername());
        if (position == null) {
            position = createPosition(current);
        }
        int quantityChange = current.getCurrentQuantity() - (previous == null ? 0 : previous.getCurrentQuantity());
        float valueChange = current.getCurrentTotalPrice() - (previous == null ? 0 : previous.getCurrentTotalPrice());

        if (current.getBuySell() == BuySell.Sell) {
            quantityChange *= -1;
            valueChange *= -1;
        }
        position.addToQuantity(quantityChange);
        position.addToTotalValue(valueChange);

        if (position.getQuantity() == 0 && position.getId() != null) {
            positionRepository.delete(position);
        } else {
            positionRepository.save(position);
        }
    }

    @Override
    public Position createPosition(Trade current) {
        Position position = new Position();
        position.setItemId(current.getItemId());
        position.setUsername(current.getUsername());
        position.setQuantity(0);
        position.setPurchasePrice(0f);
        return position;
    }

    @Override
    public List<Position> getAllPositions(String username) {
        return positionRepository.findAll();
    }
}
