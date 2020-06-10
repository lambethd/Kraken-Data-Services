package lambethd.kraken.application.mapper;

import lambethd.kraken.application.interfaces.ITradeToHistoricalTradeMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.HistoricalTrade;
import portfolio.Trade;

@Service
public class TradeToHistoricalTradeMapper implements ITradeToHistoricalTradeMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Trade mapForward(HistoricalTrade in) {
        return modelMapper.map(in, Trade.class);
    }

    @Override
    public HistoricalTrade mapBack(Trade in) {
        return modelMapper.map(in, HistoricalTrade.class);
    }
}
