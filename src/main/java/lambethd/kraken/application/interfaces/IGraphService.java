package lambethd.kraken.application.interfaces;

import org.springframework.stereotype.Service;
import runescape.Graph;

@Service
public interface IGraphService {
    Graph getGraphById(Integer id);
}
