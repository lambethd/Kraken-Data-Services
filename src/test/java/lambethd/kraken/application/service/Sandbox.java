package lambethd.kraken.application.service;

import lambethd.kraken.application.Configuration;
import lambethd.kraken.application.WebSecurityConfig;
import lambethd.kraken.application.exception.EntityNotFoundException;
import lambethd.kraken.application.exception.ForbiddenException;
import lambethd.kraken.application.interfaces.ITradeService;
import lambethd.kraken.data.mongo.repository.IItemRepository;
import lambethd.kraken.data.mongo.repository.ITradeRepository;
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
//@Ignore
public class Sandbox {
    @Autowired
    private ITradeService tradeService;

    @Autowired
    private ITradeRepository tradeRepository;

    @Autowired
    private IItemRepository itemRepository;

    @Test
    @Ignore
    public void clearAllTrades_Always_ClearsTrades() {
        tradeRepository.deleteAll();
    }

    @Test
    public void createPendingTrade_WhenProvidedValidTrade_InsertsIntoMongo() throws ForbiddenException, EntityNotFoundException {
        Trade trade = new Trade();
        trade.setItemId(39935);
        trade.setRequestPrice(205000f);
        trade.setRequestQuantity(100);
        trade.setRequestTime(LocalDateTime.now());
        trade.setTradeStatus(TradeStatus.Pending);

        tradeService.createTrade(trade, "anonymousUser");
    }

    @Test
    public void createAbortedTrade_WhenProvidedValidTrade_InsertsIntoMongo() throws ForbiddenException, EntityNotFoundException {
        Trade trade = new Trade();
        trade.setItemId(39935);
        trade.setRequestPrice(205000f);
        trade.setRequestQuantity(100);
        trade.setRequestTime(LocalDateTime.now().minusDays(2));
        trade.setTradeStatus(TradeStatus.Aborted);

        trade.setLastUpdated(LocalDateTime.now());
        trade.setCurrentTotalPrice(201000f);
        trade.setCurrentQuantity(50);
        tradeService.createTrade(trade, "devilchat");
    }

    @Test
    public void createCompletedTrade_WhenProvidedValidTrade_InsertsIntoMongo() throws ForbiddenException, EntityNotFoundException {
        Trade trade = new Trade();
        trade.setItemId(39935);
        trade.setRequestPrice(205000f);
        trade.setRequestQuantity(100);
        trade.setRequestTime(LocalDateTime.now().minusDays(2));
        trade.setTradeStatus(TradeStatus.Complete);

        trade.setCurrentTotalPrice(203000f);
        trade.setCurrentQuantity(100);
        trade.setLastUpdated(LocalDateTime.now());
        tradeService.createTrade(trade, "devilchat");
    }

    @Test
    public void createPartialTrade_WhenProvidedValidTrade_InsertsIntoMongo() throws ForbiddenException, EntityNotFoundException {
        Trade trade = new Trade();
        trade.setItemId(39935);
        trade.setRequestPrice(205000f);
        trade.setRequestQuantity(100);
        trade.setRequestTime(LocalDateTime.now());
        trade.setTradeStatus(TradeStatus.Partial);

        trade.setCurrentTotalPrice(1050000f);
        trade.setCurrentQuantity(30);
        tradeService.createTrade(trade, "devilchat");
    }
}
