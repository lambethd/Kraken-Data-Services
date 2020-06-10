package lambethd.kraken.application.behaviour.trade;

import lambethd.kraken.application.interfaces.IBehaviour;
import lambethd.kraken.application.interfaces.ITradeToHistoricalTradeMapper;
import lambethd.kraken.data.mongo.repository.ICurrentTradeRepository;
import lambethd.kraken.data.mongo.repository.ITradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.Trade;
import portfolio.TradeStatus;

@Service
public class AbortTradeBehaviour implements IBehaviour<Trade> {
    @Autowired
    private ICurrentTradeRepository currentTradeRepository;
    @Autowired
    private ITradeToHistoricalTradeMapper tradeToHistoricalTradeMapper;
    @Autowired
    private ITradeRepository tradeRepository;

    @Override
    public Trade beforeAction(Trade oldTrade, Trade newTrade) {
        newTrade.setTradeStatus(TradeStatus.Aborted);
        return newTrade;
    }

    @Override
    public Trade completeAction(Trade oldTrade, Trade newTrade) {
        currentTradeRepository.delete(newTrade);
        return tradeRepository.save(tradeToHistoricalTradeMapper.mapBack(newTrade));
    }

    @Override
    public Trade afterAction(Trade oldTrade, Trade newTrade) {
        return newTrade;
    }
}
