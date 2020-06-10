package lambethd.kraken.application.mapper;

import lambethd.kraken.application.dto.TradeDto;
import lambethd.kraken.application.exception.UnableToMapException;
import lambethd.kraken.application.interfaces.ITradeDtoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.Trade;

@Service
public class TradeDtoMapper implements ITradeDtoMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Trade mapToTrade(TradeDto tradeDto) {
        return modelMapper.map(tradeDto, Trade.class);
    }

    @Override
    public TradeDto mapToTradeDto(Trade trade) {
        return modelMapper.map(trade, TradeDto.class);
    }
}
