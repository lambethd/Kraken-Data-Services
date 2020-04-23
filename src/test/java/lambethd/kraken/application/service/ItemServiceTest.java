package lambethd.kraken.application.service;

import lambethd.kraken.application.Configuration;
import lambethd.kraken.application.interfaces.IItemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import runescape.Item;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
public class ItemServiceTest {
    @Autowired
    private IItemService itemService;

    @Test
    public void getItems_WhenItemsExist_ReturnsAListOfItems() {
        List<Item> items = itemService.getItems();
        Assert.assertNotNull(items);
        Assert.assertNotEquals(0, items.size());
    }

    @Test
    public void getItemById_GiveValidId_ReturnsTheItem(){
        Item item = itemService.getItemByName("A mis-fortune from The Mighty Zoltan (1/17)");
        Assert.assertNotNull(item);
        Assert.assertEquals("A mis-fortune from The Mighty Zoltan (1/17t)", item.name);
    }

    @Test
    public void getItemById_GiveValidIds_ReturnsTheItem(){
        Item item = itemService.getItem(39935);
        Assert.assertNotNull(item);
        Assert.assertEquals("A mis-fortune from The Mighty Zoltan (1/17)", item.name);
    }

    @Test
    public void findByNameRegex_GivenRegex_Returns(){
        List<Item> items = itemService.getItemsByNameSearch(".*Akrisae's hood.*");
        Assert.assertNotNull(items);
        Assert.assertNotEquals(0, items.size());
        Assert.assertEquals(2, items.size());
    }
}