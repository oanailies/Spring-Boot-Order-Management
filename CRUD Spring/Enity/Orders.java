package com.example.CRUD_Tutorial.Entity;

import jakarta.persistence.*;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private Integer totalQuantity;

    @Column(nullable = false)
    private Float totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "order_menu",
            joinColumns = @JoinColumn(name = "idOrder"),
            inverseJoinColumns = @JoinColumn(name = "idMenu")
    )
    private List<Menu> menus = new ArrayList<>();

    public Orders() {
    }

    public Orders(LocalDateTime orderDate, OrderStatus orderStatus, Integer totalQuantity, Float totalPrice) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Integer getId() {
        return idOrder;
    }

    public void setId(Integer id) {
        this.idOrder = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + idOrder +
                ", orderDate=" + orderDate +
                ", orderStatus=" + orderStatus +
                ", totalQuantity=" + totalQuantity +
                '}';
    }
}
