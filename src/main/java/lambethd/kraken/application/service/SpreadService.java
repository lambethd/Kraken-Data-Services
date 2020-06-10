package lambethd.kraken.application.service;

import lambethd.kraken.application.interfaces.ISpreadService;
import lambethd.kraken.application.strategies.spread.CreateSpreadStrategy;
import lambethd.kraken.data.mongo.repository.ISpreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import portfolio.Spread;

import java.util.List;

@Service
public class SpreadService implements ISpreadService {

    @Autowired
    private ISpreadRepository spreadRepository;
    @Autowired
    private CreateSpreadStrategy createSpreadStrategy;

    @Override
    public Spread createSpread(Spread spread) {
        return createSpreadStrategy.completeAll(null, spread);
    }

    @Override
    public List<Spread> getSpreads(String username) {
        return spreadRepository.getSpreadByUsername(username, new Sort(Sort.Direction.DESC, "lastUpdated"));
    }
}
