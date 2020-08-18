package lambethd.kraken.application.service;

import lambethd.kraken.application.interfaces.IPnlService;
import lambethd.kraken.application.interfaces.IPositionService;
import lambethd.kraken.application.interfaces.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.HistoricalTrade;
import portfolio.Pnl;
import portfolio.Position;
import portfolio.Trade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PnlService implements IPnlService {

    @Autowired
    private ITradeService tradeService;
    @Autowired
    private IPositionService positionService;

    @Override
    public List<Pnl> getPnl(String username) {
        return null;
    }

    @Override
    public Pnl getPnl(String username, LocalDateTime date) {
        List<Trade> trades = tradeService.getCurrentTrades(username);
        List<HistoricalTrade> historicalTrades = tradeService.getHistoricalTrades(username);
        List<Position> positions = positionService.getAllPositions(username);
        return getPnl(date, trades, historicalTrades, positions);
    }

    @Override
    public List<Pnl> getPnl(String username, LocalDateTime rangeStart, LocalDateTime rangeEnd) {
        List<Pnl> pnls = new ArrayList<>();
        for (LocalDateTime current = rangeStart; current.isBefore(rangeEnd); current = current.plusDays(1)) {
            pnls.add(getPnl(username, current));
        }
        return pnls;
    }

    private Pnl getPnl(LocalDateTime date, List<Trade> trades, List<HistoricalTrade> historicalTrades, List<Position> positions) {
        //List<Trade> tradesForDate = trades.stream().filter(t->t.);
        Pnl pnl = new Pnl();
        pnl.setDate(date);


        float pnlValue = 0;
        return pnl;
    }
}
