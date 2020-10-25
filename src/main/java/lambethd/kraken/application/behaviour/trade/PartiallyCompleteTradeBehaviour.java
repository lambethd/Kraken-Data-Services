package lambethd.kraken.application.behaviour.trade;

import lambethd.kraken.application.interfaces.IBehaviour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.Trade;
import portfolio.TradeEntry;
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
        newTrade.setPreviousEntries(oldTrade.getPreviousEntries());
        newTrade.getPreviousEntries().add(new TradeEntry(oldTrade.getLastUpdated(), oldTrade.getCurrentQuantity(), oldTrade.getCurrentTotalPrice()));
        return currentTradeRepository.save(newTrade);
    }

    @Override
    public Trade afterAction(Trade oldTrade, Trade newTrade) {
        return newTrade;
    }
}
