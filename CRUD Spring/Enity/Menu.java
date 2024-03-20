package com.example.CRUD_Tutorial.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMenu")
    private Integer idMenu;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Integer stock;

    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;

    @ManyToMany(mappedBy = "menus")
    private List<Orders> orders = new ArrayList<>();

    public Menu() {
    }

    public Menu(String name, Float price, Integer stock, byte[] image) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }

    public Integer getId() {
        return idMenu;
    }

    public void setId(Integer id) {
        this.idMenu = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + idMenu +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}