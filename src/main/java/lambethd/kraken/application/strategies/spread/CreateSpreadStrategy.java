package lambethd.kraken.application.strategies.spread;

import lambethd.kraken.application.behaviour.common.UpdateLastUpdatedBehaviour;
import lambethd.kraken.application.behaviour.common.ValidatedBehaviour;
import lambethd.kraken.application.behaviour.spread.CreateSpreadBehaviour;
import lambethd.kraken.application.behaviour.trade.CreateTradeBehaviour;
import lambethd.kraken.application.interfaces.IBehaviour;
import lambethd.kraken.application.strategies.common.StrategyBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.Spread;
import portfolio.Trade;

import java.util.ArrayList;

@Service
public class CreateSpreadStrategy extends StrategyBase<Spread> {
    @Autowired
    private CreateSpreadBehaviour createSpreadBehaviour;
    @Autowired
    private ValidatedBehaviour<Spread> validatedSpreadBehaviour;
    @Autowired
    private UpdateLastUpdatedBehaviour<Spread> updateLastUpdatedBehaviour;

    @Override
    protected ArrayList<IBehaviour<Spread>> getBehaviours() {
        ArrayList<IBehaviour<Spread>> behaviours = new ArrayList<>();
        behaviours.add(validatedSpreadBehaviour);
        behaviours.add(createSpreadBehaviour);
        behaviours.add(updateLastUpdatedBehaviour);
        return behaviours;
    }
}
