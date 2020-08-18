package lambethd.kraken.application.service;

import lambethd.kraken.application.interfaces.IPnlService;
import lambethd.kraken.application.interfaces.IPositionService;
import lambethd.kraken.application.interfaces.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.Pnl;
import portfolio.Position;
import portfolio.Trade;

import java.time.LocalDateTime;
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
        return null;
    }

    @Override
    public List<Pnl> getPnl(String username, LocalDateTime rangeStart, LocalDateTime rangeEnd) {
        return null;
    }

    private Pnl getPnl(LocalDateTime date, List<Trade> trades, List<Position> positions){
        Pnl pnl = new Pnl();
        pnl.setDate(date);

        float pnlValue = 0;
        return pnl;
    }
}
