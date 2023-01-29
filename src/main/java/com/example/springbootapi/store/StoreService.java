package com.example.springbootapi.store;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    @Transactional
    public void updateStore(Long storeId, String name, String address) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalStateException("Store with id " + storeId + " does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(store.getName(), name)){
            Optional<Store> storeOptional = storeRepository.findStoreByName(name);
            if(storeOptional.isPresent()){
                throw new IllegalStateException("A store with the name " + name + " already exists. Pick a different name to update store.");
            }

            store.setName(name);
        }

        if(address != null && address.length() > 0 && !Objects.equals(store.getAddress(), address)){
            store.setAddress(address);
        }
    }
}
