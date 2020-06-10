package lambethd.kraken.application.strategies.trade;

import lambethd.kraken.application.behaviour.trade.PartiallyCompleteTradeBehaviour;
import lambethd.kraken.application.behaviour.trade.PositionEffectingTradeBehaviour;
import lambethd.kraken.application.behaviour.common.UpdateLastUpdatedBehaviour;
import lambethd.kraken.application.behaviour.common.ValidatedBehaviour;
import lambethd.kraken.application.interfaces.IBehaviour;
import lambethd.kraken.application.strategies.common.StrategyBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.Trade;

import java.util.ArrayList;

@Service
public class PartiallyCompleteTradeStrategy extends StrategyBase<Trade> {
    @Autowired
    private PartiallyCompleteTradeBehaviour partiallyCompleteTradeBehaviour;
    @Autowired
    private UpdateLastUpdatedBehaviour<Trade> updateLastUpdatedBehaviour;
    @Autowired
    private ValidatedBehaviour<Trade> validatedTradeBehaviour;
    @Autowired
    private PositionEffectingTradeBehaviour positionEffectingTradeBehaviour;

    @Override
    protected ArrayList<IBehaviour<Trade>> getBehaviours() {
        ArrayList<IBehaviour<Trade>> behaviours = new ArrayList<>();
        behaviours.add(validatedTradeBehaviour);
        behaviours.add(partiallyCompleteTradeBehaviour);
        behaviours.add(updateLastUpdatedBehaviour);
        behaviours.add(positionEffectingTradeBehaviour);
        return behaviours;
    }
}
