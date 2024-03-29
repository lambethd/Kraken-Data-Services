package lambethd.kraken.application.service;

import lambethd.kraken.application.interfaces.IItemService;
import lambethd.kraken.data.mongo.repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import runescape.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements IItemService {
    @Autowired
    private IItemRepository itemRepository;

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public Item getItem(Integer itemId) {
        return itemRepository.getItemById(itemId);
    }

    @Override
    public Item getItemByName(String name) {
        Optional<Item> item = itemRepository.getItemsByName(name, new Sort(Sort.Direction.ASC, "name")).stream().findFirst();
        return item.orElse(null);
    }

    @Override
    public List<Item> getItemsByNameSearch(String searchString) {
        return itemRepository.findByNameRegex(searchString, new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public List<String> getItemNames() {
        List<String> names = new ArrayList<>();
        getItems().forEach(i -> names.add(i.name));
        return names;
    }
}
