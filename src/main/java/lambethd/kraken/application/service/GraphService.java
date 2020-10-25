package lambethd.kraken.application.service;

import lambethd.kraken.application.interfaces.IGraphService;
import lambethd.kraken.application.interfaces.IRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.RangeType;
import runescape.Graph;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class GraphService implements IGraphService {
    @Autowired
    private IGraphRepository graphRepository;
    @Autowired
    private IRangeService rangeService;

    @Override
    public Graph getGraph(Integer id) {
        return graphRepository.getGraphById(id);
    }

    @Override
    public Graph getGraph(Integer id, RangeType rangeType) {
        Graph graph = graphRepository.getGraphById(id);
        trimGraph(graph, rangeService.getDateFromRange(rangeType));
        return graph;
    }

    private void trimGraph(Graph graph, LocalDateTime startDate) {
        graph.daily = graph.daily.stream().filter(g -> g.getKey().isAfter(startDate)).collect(Collectors.toList());
    }
}
