package lambethd.kraken.application.interfaces;

import lambethd.kraken.application.dto.TradeDto;
import lambethd.kraken.application.exception.UnableToMapException;
import portfolio.Trade;

public interface ITradeDtoMapper {
    Trade mapToTrade(TradeDto tradeDto) throws UnableToMapException;

    TradeDto mapToTradeDto(Trade trade) throws UnableToMapException;
}
