package lambethd.kraken.application.service;

import lambethd.kraken.application.Configuration;
import lambethd.kraken.application.MongoConfiguration;
import lambethd.kraken.application.WebSecurityConfig;
import lambethd.kraken.application.interfaces.IValidator;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import portfolio.Trade;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Configuration.class, WebSecurityConfig.class, MongoConfiguration.class})
@Ignore
public class TradeServiceTest {
    @Mock
    private ICurrentTradeRepository currentTradeRepository;
    @Mock
    private ITradeRepository tradeRepository;
    @Mock
    private IValidator<Trade> tradeValidator;
    @InjectMocks
    private TradeService tradeService;

}