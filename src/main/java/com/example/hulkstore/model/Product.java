package com.example.hulkstore.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name ="name")
    @NotBlank(message = "Name is required!")
    private String name;

    public Product() {
    }

    public Product(Long id, @NotBlank(message = "Name is required!") String name, @NotBlank(message = "Price is required!") int price, @NotNull(message = "Stock cannot be null") int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Column(name ="price")
    @NotBlank(message = "Price is required!")
    private int price;
    @Column(name ="stock")
    @NotNull(message = "Stock cannot be null")
    private int stock;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                '}';
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
