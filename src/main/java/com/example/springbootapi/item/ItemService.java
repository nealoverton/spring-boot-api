package com.example.springbootapi.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public void addNewItem(Item item) {
        Optional<Item> itemOptional = itemRepository.findItemByNameAndColour(item.getName(), item.getColour());
        if(itemOptional.isPresent()) {
            throw new IllegalStateException("That item already exists. Pick a new name or colour to add to inventory.");
        }
        itemRepository.save(item);
    }
}
