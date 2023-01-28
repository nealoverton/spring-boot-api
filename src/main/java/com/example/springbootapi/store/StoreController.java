package com.example.springbootapi.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/store")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<Store> getStores() {
        return storeService.getStores();
    }

    @GetMapping(path = "{storeId}")
    public Optional<Store> getStoreById(@PathVariable("storeId") Long storeId){
        return storeService.getStoreById(storeId);
    }

    @PostMapping
    public void createNewStore(@RequestBody Store store) {
        storeService.addNewStore(store);
    }
}
