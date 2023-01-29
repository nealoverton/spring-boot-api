package com.example.springbootapi.item;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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

    public ItemStockists getItemById(Long itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);

        if(!itemOptional.isPresent()) {
            throw new IllegalStateException("Item not found");
        }

        Item item = itemOptional.get();
        Set stores = item.getStores();

        return new ItemStockists(item, stores);
    }

    public void addNewItem(Item item) {
        Optional<Item> itemOptional = itemRepository.findItemByNameAndColour(item.getName(), item.getColour());
        if(itemOptional.isPresent()) {
            throw new IllegalStateException("That item already exists. Pick a different name or colour to add to inventory.");
        }
        itemRepository.save(item);
    }

    public void deleteItem(Long itemId) {
        boolean exists = itemRepository.existsById(itemId);

        if(!exists){
            throw new IllegalStateException("Item with id " + itemId + " does not exist");
        }
        itemRepository.deleteById(itemId);
    }

    @Transactional
    public void updateItem(Long itemId, String name, String colour, Double price) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("Item with id " + itemId + " does not exist"));

        if(name != null && name.length() > 0 && colour != null && colour.length() > 0) {
            Optional<Item> itemOptional = itemRepository.findItemByNameAndColour(name, colour);
            if(itemOptional.isPresent()){
                throw new IllegalStateException("That item already exists. Pick a different name or colour to update inventory.");
            }
        }

        if(name != null && name.length() > 0 && !Objects.equals(item.getName(), name)) {
            Optional<Item> itemOptional = itemRepository.findItemByNameAndColour(name, item.getColour());
            if(itemOptional.isPresent()){
                throw new IllegalStateException("That item already exists. Pick a different name or colour to update inventory.");
            }

            item.setName(name);
        }

        if(colour != null && colour.length() > 0 && !Objects.equals(item.getColour(), colour)) {
            Optional<Item> itemOptional = itemRepository.findItemByNameAndColour(item.getName(), colour);
            if(itemOptional.isPresent()){
                throw new IllegalStateException("That item already exists. Pick a different name or colour to update inventory.");
            }

            item.setColour(colour);
        }

        if(price != null && price > 0.00 && !Objects.equals(item.getPrice(), price)) {
            item.setPrice(price);
        }
    }
}
