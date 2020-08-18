package lambethd.kraken.application.interfaces;

import org.springframework.stereotype.Service;
import portfolio.RangeType;
import runescape.Graph;

@Service
public interface IGraphService {
    Graph getGraph(Integer id);
    Graph getGraph(Integer id, RangeType rangeType);
}
