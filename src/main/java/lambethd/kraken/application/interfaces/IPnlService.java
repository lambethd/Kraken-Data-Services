package lambethd.kraken.application.interfaces;

import portfolio.Pnl;

import java.time.LocalDateTime;
import java.util.List;

public interface IPnlService {
    List<Pnl> getPnl(String username);

    Pnl getPnl(String username, LocalDateTime date);

    List<Pnl> getPnl(String username, LocalDateTime rangeStart, LocalDateTime rangeEnd);
}
