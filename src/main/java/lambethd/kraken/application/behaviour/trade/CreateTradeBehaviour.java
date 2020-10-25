package lambethd.kraken.application.behaviour.trade;

import lambethd.kraken.application.interfaces.IAuthService;
import lambethd.kraken.application.interfaces.IBehaviour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.Trade;
import portfolio.TradeStatus;

@Service
public class CreateTradeBehaviour implements IBehaviour<Trade> {
    @Autowired
    private ICurrentTradeRepository currentTradeRepository;
    @Autowired
    private IAuthService authService;

    @Override
    public Trade beforeAction(Trade oldTrade, Trade newTrade) {
        newTrade.setTradeStatus(TradeStatus.Pending);
        newTrade.setUsername(authService.getCurrentUser());
        return newTrade;
    }

    @Override
    public Trade completeAction(Trade oldTrade, Trade newTrade) {
        return currentTradeRepository.save(newTrade);
    }

    @Override
    public Trade afterAction(Trade oldTrade, Trade newTrade) {
        return newTrade;
    }
}
