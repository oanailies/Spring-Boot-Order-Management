package com.example.CRUD_Tutorial.Service;


import com.example.CRUD_Tutorial.Entity.Menu;
import com.example.CRUD_Tutorial.Entity.OrderStatus;
import com.example.CRUD_Tutorial.Entity.Orders;
import com.example.CRUD_Tutorial.Entity.User;
import com.example.CRUD_Tutorial.Repository.MenuRepository;
import com.example.CRUD_Tutorial.Repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
    }

    public List<Orders> getAllOrders() {
        return (List<Orders>) orderRepository.findAll();
    }

    public Optional<Orders> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public void saveOrder(Orders order) {
        orderRepository.save(order);
    }

    public void createOrder(Orders order) {
        saveOrder(order);
    }

    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }


    @Transactional
    public void createOrderWithMenus(Integer userId, List<Integer> menuIds) {


        Orders order = new Orders();
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.NEW);
        order.setTotalQuantity(menuIds.size());
        order.setId(userId);

        Float totalPrice = 0.0f;
        for (Integer menuId : menuIds) {
            Menu menu = menuRepository.findById(menuId).orElse(null);
            if (menu != null && menu.getStock() > 0) {
                order.getMenus().add(menu);
                menu.setStock(menu.getStock() - 1);
                totalPrice += menu.getPrice();
            }
        }

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
    }

    @Transactional
    public void updateOrderStatus(Integer orderId, OrderStatus newStatus) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID must not be null");
        }

        if (newStatus == null) {
            throw new IllegalArgumentException("Order status must not be null");
        }

        Optional<Orders> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();
            order.setOrderStatus(newStatus);
            orderRepository.save(order);
        } else {
            throw new EntityNotFoundException("Order not found with ID: " + orderId);
        }
    }



    public List<Orders> listAll() {
        return (List<Orders>) orderRepository.findAll();
    }

    public Orders get(Integer id) {

        Optional<Orders> result = orderRepository.findById(id);
        if(result.isPresent())
        {
            return result.get();
        }
        throw new NotFoundException("nu s a gasit userul");

    }
}