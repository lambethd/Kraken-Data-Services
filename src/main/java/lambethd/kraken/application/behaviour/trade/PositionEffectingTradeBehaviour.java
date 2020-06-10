package lambethd.kraken.application.behaviour.trade;

import lambethd.kraken.application.interfaces.IBehaviour;
import lambethd.kraken.application.interfaces.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.Trade;

@Service
public class PositionEffectingTradeBehaviour implements IBehaviour<Trade> {
    @Autowired
    private IPositionService positionService;

    @Override
    public Trade beforeAction(Trade oldTrade, Trade newTrade) {
        return newTrade;
    }

    @Override
    public Trade completeAction(Trade oldTrade, Trade newTrade) {
        return newTrade;
    }

    @Override
    public Trade afterAction(Trade oldTrade, Trade newTrade) {
        positionService.applyToPosition(newTrade, oldTrade);
        return newTrade;
    }
}
