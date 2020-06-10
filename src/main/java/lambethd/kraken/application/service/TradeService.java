package lambethd.kraken.application.service;

import lambethd.kraken.application.exception.EntityNotFoundException;
import lambethd.kraken.application.exception.ForbiddenException;
import lambethd.kraken.application.interfaces.*;
import lambethd.kraken.application.strategies.trade.AbortTradeStrategy;
import lambethd.kraken.application.strategies.trade.CompleteTradeStrategy;
import lambethd.kraken.application.strategies.trade.CreateTradeStrategy;
import lambethd.kraken.application.strategies.trade.PartiallyCompleteTradeStrategy;
import lambethd.kraken.data.mongo.repository.ICurrentTradeRepository;
import lambethd.kraken.data.mongo.repository.ITradeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import portfolio.HistoricalTrade;
import portfolio.Trade;

import java.util.List;

@Service
public class TradeService implements ITradeService {
    @Autowired
    private ICurrentTradeRepository currentTradeRepository;
    @Autowired
    private ITradeRepository tradeRepository;
    @Autowired
    private CreateTradeStrategy tradeCreateStrategy;
    @Autowired
    private PartiallyCompleteTradeStrategy partiallyCompleteTradeStrategy;
    @Autowired
    private CompleteTradeStrategy completeTradeStrategy;
    @Autowired
    private AbortTradeStrategy abortTradeStrategy;

    @Override
    public List<Trade> getCurrentTrades(String username) {
        return currentTradeRepository.findTradeByUsername(username, new Sort(Sort.Direction.DESC, "lastUpdated"));
    }

    public Trade completeAction(Trade trade, IStrategy<Trade> strategy, String username) throws ForbiddenException, EntityNotFoundException {
        Trade oldTrade = checkUserOwnsTrade(trade.getId(), username);
        strategy.completeAll(oldTrade, trade);
        return trade;
    }

    @Override
    public Trade createTrade(Trade trade, String username) throws ForbiddenException, EntityNotFoundException {
        trade.setUsername(username);
        return completeAction(trade, tradeCreateStrategy, username);
    }

    @Override
    public Trade partiallyCompleteTrade(Trade trade, String username) throws ForbiddenException, EntityNotFoundException {
        return completeAction(trade, partiallyCompleteTradeStrategy, username);
    }

    @Override
    public Trade completeTrade(Trade trade, String username) throws ForbiddenException, EntityNotFoundException {
        return completeAction(trade, completeTradeStrategy, username);
    }

    @Override
    public Trade abortTrade(Trade trade, String username) throws ForbiddenException, EntityNotFoundException {
        return completeAction(trade, abortTradeStrategy, username);
    }

    @Override
    public List<HistoricalTrade> getHistoricalTrades(String username) {
        return tradeRepository.findTradeByUsername(username, new Sort(Sort.Direction.DESC, "lastUpdated"));
    }

    private Trade checkUserOwnsTrade(String tradeId, String username) throws ForbiddenException, EntityNotFoundException {
        if (tradeId == null) {
            return null;
        }
        Trade trade = currentTradeRepository.findTradeById(new ObjectId(tradeId));
        if (trade == null) {
            throw new EntityNotFoundException("Trade " + tradeId + " does not exist");
        }
        if (!trade.getUsername().equalsIgnoreCase(username)) {
            throw new ForbiddenException("Trade does not belong to user '" + username + "'.");
        }
        return trade;
    }
}
