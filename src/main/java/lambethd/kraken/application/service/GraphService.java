package lambethd.kraken.application.service;

import lambethd.kraken.application.interfaces.IGraphService;
import lambethd.kraken.data.mongo.repository.IGraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runescape.Graph;

@Service
public class GraphService implements IGraphService {
    @Autowired
    private IGraphRepository graphRepository;

    @Override
    public Graph getGraphById(Integer id) {
        return graphRepository.getGraphByItemId(id);
    }
}
