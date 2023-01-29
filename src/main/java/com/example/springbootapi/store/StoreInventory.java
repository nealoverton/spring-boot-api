package com.example.springbootapi.store;

import com.example.springbootapi.item.Item;

import java.util.Set;

public class StoreInventory {
    private Store store;
    private Set<Item> inventory;

    public StoreInventory(Store store, Set<Item> inventory) {
        this.store = store;
        this.inventory = inventory;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Set<Item> getInventory() {
        return inventory;
    }

    public void setInventory(Set<Item> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "StoreInventory{" +
                "store=" + store +
                ", inventory=" + inventory +
                '}';
    }
}
