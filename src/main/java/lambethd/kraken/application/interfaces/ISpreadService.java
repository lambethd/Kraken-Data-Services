package lambethd.kraken.application.interfaces;

import portfolio.Spread;

import java.util.List;

public interface ISpreadService {
    Spread createSpread(Spread spread);
    List<Spread> getSpreads(String username);
}
