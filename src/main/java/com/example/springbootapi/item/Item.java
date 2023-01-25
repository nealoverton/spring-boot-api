package com.example.springbootapi.item;

import jakarta.persistence.*;

@Entity
@Table
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    private Long id;
    private String name;
    private String colour;
    private Double price;
    private Integer quantity;

    @Transient
    private Boolean inStock;

    public Item() {
    }

    public Item(Long id, String name, String colour, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.colour = colour;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(String name, String colour, Double price, Integer quantity) {
        this.name = name;
        this.colour = colour;
        this.price = price;
        this.quantity = quantity;
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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getInStock() {
        return this.quantity > 0;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
