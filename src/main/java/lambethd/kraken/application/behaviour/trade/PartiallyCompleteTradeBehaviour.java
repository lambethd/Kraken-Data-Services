package lambethd.kraken.application.behaviour.trade;

import lambethd.kraken.application.interfaces.IBehaviour;
import lambethd.kraken.data.mongo.repository.ICurrentTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.Trade;
import portfolio.TradeStatus;

@Service
public class PartiallyCompleteTradeBehaviour implements IBehaviour<Trade> {
    @Autowired
    private ICurrentTradeRepository currentTradeRepository;

    @Override
    public Trade beforeAction(Trade oldTrade, Trade newTrade) {
        newTrade.setTradeStatus(TradeStatus.Partial);
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
