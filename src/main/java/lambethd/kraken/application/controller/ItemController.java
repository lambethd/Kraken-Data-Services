package lambethd.kraken.application.controller;

import lambethd.kraken.application.interfaces.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import runescape.Item;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private IItemService itemService;

    @RequestMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return buildResponseEntity(itemService.getItems());
    }

    @RequestMapping("/{itemId}")
    public ResponseEntity<Item> getItem(@PathVariable Integer itemId) {
        return buildResponseEntity(itemService.getItem(itemId));
    }

    @RequestMapping("/search/{searchString}")
    public ResponseEntity<List<Item>> getItems(@RequestParam String searchString) {
        return buildResponseEntity(itemService.getItemsByNameSearch(searchString));
    }

    @RequestMapping("/names")
    public ResponseEntity<List<String>> getItemNames() {
        return buildResponseEntity(itemService.getItemNames());
    }
}
