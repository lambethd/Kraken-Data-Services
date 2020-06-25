package lambethd.kraken.application.controller;

import lambethd.kraken.application.interfaces.IGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import runescape.Graph;
import runescape.Item;

@RestController
@RequestMapping("/graph")
public class GraphController extends BaseController {

    @Autowired
    private IGraphService graphService;

    @RequestMapping("/{itemId}")
    public ResponseEntity<Graph> getGraph(@PathVariable Integer itemId) {
        return buildResponseEntity(graphService.getGraphById(itemId));
    }
}
