package com.example.springbootapi.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        System.out.println("in item controller");
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @PostMapping
    public void createNewItem(@RequestBody Item item){
        itemService.addNewItem(item);
    }

    @DeleteMapping(path = "{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }

    @PutMapping(path = "{itemId}")
    public void updateItem(
            @PathVariable("itemId") Long itemId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String colour,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Integer quantity) {
        itemService.updateItem(itemId, name, colour, price, quantity);
    }
}
