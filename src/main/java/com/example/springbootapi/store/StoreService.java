package com.example.springbootapi.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getStores() {
        return storeRepository.findAll();
    }

    public Optional<Store> getStoreById(Long storeId) {
        Optional<Store> storeOptional = storeRepository.findById(storeId);

        if(!storeOptional.isPresent()) {
            throw new IllegalStateException("Store not found");
        }

        return storeOptional;
    }

    public void addNewStore(Store store) {
        Optional<Store> storeOptional = storeRepository.findStoreByName(store.getName());

        if(storeOptional.isPresent()){
            throw new IllegalStateException("A store with that name already exists. Pick another name to add store.");
        }

        storeRepository.save(store);
    }

    public void deleteStore(Long storeId) {
        boolean exists = storeRepository.existsById(storeId);

        if(!exists){
            throw new IllegalStateException("Store with id " + storeId + " does not exist");
        }

        storeRepository.deleteById(storeId);
    }
}
