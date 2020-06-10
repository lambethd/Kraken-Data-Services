package lambethd.kraken.application.controller;

import lambethd.kraken.application.dto.TradeDto;
import lambethd.kraken.application.exception.EntityNotFoundException;
import lambethd.kraken.application.exception.ForbiddenException;
import lambethd.kraken.application.exception.TradeFlowException;
import lambethd.kraken.application.exception.UnableToMapException;
import lambethd.kraken.application.interfaces.IAuthService;
import lambethd.kraken.application.interfaces.ITradeDtoMapper;
import lambethd.kraken.application.interfaces.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.HistoricalTrade;
import portfolio.Trade;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/trade")
public class TradeController extends BaseController {

    @Autowired
    private IAuthService authService;
    @Autowired
    private ITradeService tradeService;
    @Autowired
    private ITradeDtoMapper tradeDtoMapper;

    @GetMapping("/current")
    public ResponseEntity<List<Trade>> getAllTrades() {
        return buildResponseEntity(tradeService.getCurrentTrades(authService.getCurrentUser()));
    }

    @PutMapping("/current")
    public ResponseEntity<Trade> createTrade(@RequestBody TradeDto tradeDto) throws UnableToMapException, ForbiddenException, EntityNotFoundException {
        Trade trade = tradeDtoMapper.mapToTrade(tradeDto);
        return buildResponseEntity(tradeService.createTrade(trade, authService.getCurrentUser()));
    }

    @PostMapping("/current")
    public ResponseEntity<Trade> updateTrade(@RequestBody TradeDto tradeDto) throws ForbiddenException, EntityNotFoundException, UnableToMapException, TradeFlowException {
        Trade trade = tradeDtoMapper.mapToTrade(tradeDto);
        switch (trade.getTradeStatus()) {
            case Partial:
                return buildResponseEntity(tradeService.partiallyCompleteTrade(trade, authService.getCurrentUser()));
            case Complete:
                return buildResponseEntity(tradeService.completeTrade(trade, authService.getCurrentUser()));
            case Aborted:
                return buildResponseEntity(tradeService.abortTrade(trade, authService.getCurrentUser()));
        }
        throw new TradeFlowException("Trade of status + " + trade.getTradeStatus() + " is invalid");
    }

    @GetMapping("/historical")
    public ResponseEntity<List<HistoricalTrade>> getHistoricalTrades() {
        return buildResponseEntity(tradeService.getHistoricalTrades(authService.getCurrentUser()));
    }
}
