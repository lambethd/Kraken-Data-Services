package lambethd.kraken.application.interfaces;

import lambethd.kraken.application.exception.EntityNotFoundException;
import lambethd.kraken.application.exception.ForbiddenException;
import portfolio.HistoricalTrade;
import portfolio.Trade;

import java.util.List;

public interface ITradeService {
    List<Trade> getCurrentTrades(String username);

    Trade createTrade(Trade trade, String username) throws ForbiddenException, EntityNotFoundException;

    Trade partiallyCompleteTrade(Trade trade, String username) throws ForbiddenException, EntityNotFoundException;

    Trade completeTrade(Trade trade, String username) throws ForbiddenException, EntityNotFoundException;

    Trade abortTrade(Trade trade, String username) throws ForbiddenException, EntityNotFoundException;


    List<HistoricalTrade> getHistoricalTrades(String username);
}
