package com.example.springbootapi.store;

import com.example.springbootapi.item.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stores")
public class Store {
    @Id
    @SequenceGenerator(
            name = "store_sequence",
            sequenceName = "store_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "store_sequence"
    )
    private Long id;
    private String name;
    private String address;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "store_items",
            joinColumns = { @JoinColumn(name = "tutorial_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    @JsonIgnore
    private Set<Item> items = new HashSet<>();

    public Store() {
    }

    public Store(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addItem(Item item){
        this.items.add(item);
        item.getStores().add(this);
    }

    public void removeItem(long itemId){
        Item item = this.items.stream().filter(i -> i.getId() == itemId).findFirst().orElse(null);
        if (item != null) {
            this.items.remove(item);
            item.getStores().remove(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
