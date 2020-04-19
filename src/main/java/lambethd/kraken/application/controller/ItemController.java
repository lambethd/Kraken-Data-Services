package lambethd.kraken.application.controller;

import lambethd.kraken.application.interfaces.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import runescape.Item;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private IItemService itemService;

    @RequestMapping
    public List<Item> getAllItems(){
        return itemService.getItems();
    }

    @RequestMapping("/{itemId}")
    public Item getItem(@PathVariable Integer itemId){
        return itemService.getItem(itemId);
    }

    @RequestMapping("/search/{searchString}")
    public List<Item> getItems(@RequestParam String searchString){
        return itemService.getItemsByNameSearch(searchString);
    }

    @RequestMapping("/names")
    public List<String> getItemNames(){
        return itemService.getItemNames();
    }
}
