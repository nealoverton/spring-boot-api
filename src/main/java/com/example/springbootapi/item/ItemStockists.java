package com.example.springbootapi.item;

import com.example.springbootapi.store.Store;

import java.util.Set;

public class ItemStockists {
    private Item item;
    private Set<Store> stores;

    public ItemStockists(Item item, Set<Store> stores) {
        this.item = item;
        this.stores = stores;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    @Override
    public String toString() {
        return "ItemStockists{" +
                "item=" + item +
                ", stores=" + stores +
                '}';
    }
}
