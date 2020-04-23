package lambethd.kraken.application.controller;

import lambethd.kraken.application.interfaces.IGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import runescape.Graph;
import runescape.Item;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/graph")
public class GraphController {

    @Autowired
    private IGraphService graphService;

    @RequestMapping("/{itemId}")
    public Graph getGraph(@PathVariable Integer itemId) {
        return graphService.getGraphById(itemId);
    }
}
