package lambethd.kraken.application.behaviour.trade;

import lambethd.kraken.application.interfaces.IBehaviour;
import lambethd.kraken.application.interfaces.ITradeToHistoricalTradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.Trade;
import portfolio.TradeEntry;
import portfolio.TradeStatus;

@Service
public class CompleteTradeBehaviour implements IBehaviour<Trade> {
    @Autowired
    private ICurrentTradeRepository currentTradeRepository;
    @Autowired
    private ITradeToHistoricalTradeMapper tradeToHistoricalTradeMapper;
    @Autowired
    private ITradeRepository tradeRepository;

    @Override
    public Trade beforeAction(Trade oldTrade, Trade newTrade) {
        newTrade.setTradeStatus(TradeStatus.Complete);
        return newTrade;
    }

    @Override
    public Trade completeAction(Trade oldTrade, Trade newTrade) {
        currentTradeRepository.delete(newTrade);
        newTrade.setPreviousEntries(oldTrade.getPreviousEntries());
        newTrade.getPreviousEntries().add(new TradeEntry(oldTrade.getLastUpdated(), oldTrade.getCurrentQuantity(), oldTrade.getCurrentTotalPrice()));
        return tradeRepository.save(tradeToHistoricalTradeMapper.mapBack(newTrade));
    }

    @Override
    public Trade afterAction(Trade oldTrade, Trade newTrade) {
        return newTrade;
    }
}
