package lambethd.kraken.application.interfaces;

import org.springframework.stereotype.Service;
import runescape.Item;

import java.util.List;

@Service
public interface IItemService {
    List<Item> getItems();

    Item getItem(Integer itemId);

    Item getItemByName(String name);

    List<Item> getItemsByNameSearch(String searchString);

    List<String> getItemNames();
}
