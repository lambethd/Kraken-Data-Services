package lambethd.kraken.application.mapper;

import lambethd.kraken.application.Configuration;
import lambethd.kraken.application.WebSecurityConfig;
import lambethd.kraken.application.dto.TradeDto;
import lambethd.kraken.application.exception.UnableToMapException;
import lambethd.kraken.application.interfaces.ITradeDtoMapper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import portfolio.Trade;
import portfolio.TradeStatus;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class, WebSecurityConfig.class})
@Ignore
public class TradeDtoMapperTest {
    @Autowired
    private ITradeDtoMapper tradeDtoMapper;

    @Test
    public void mapToTrade_whenGivenValidTradeDtoForPending_returnsATradeOfTheCorrectType() throws UnableToMapException {
        TradeDto tradeDto = new TradeDto();
        tradeDto.setId("Id");
        tradeDto.setItemId(39935);
        tradeDto.setRequestQuantity(100);
        tradeDto.setRequestPrice(1001f);
        tradeDto.setTradeStatus(TradeStatus.Pending);
        tradeDto.setUsername("username");
        tradeDto.setRequestTime(LocalDateTime.now());
        Trade trade = tradeDtoMapper.mapToTrade(tradeDto);

        Assert.assertNotNull(trade);
        Assert.assertEquals(Trade.class, trade.getClass());
        Assert.assertEquals("Id", trade.getId());
        Assert.assertEquals(Integer.valueOf(39935), trade.getItemId());
        Assert.assertEquals(Integer.valueOf(100), trade.getRequestQuantity());
        Assert.assertEquals(1001f, trade.getRequestPrice(), 0);
        Assert.assertEquals("username", trade.getUsername());
    }
}