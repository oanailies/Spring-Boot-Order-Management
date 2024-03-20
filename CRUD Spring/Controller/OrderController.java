package com.example.CRUD_Tutorial.Controller;

import com.example.CRUD_Tutorial.Entity.OrderStatus;
import com.example.CRUD_Tutorial.Entity.Orders;
import com.example.CRUD_Tutorial.Entity.User;
import com.example.CRUD_Tutorial.Repository.MenuRepository;
import com.example.CRUD_Tutorial.Service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final MenuRepository menuRepository;

    @Autowired
    public OrderController(OrderService orderService, MenuRepository menuRepository) {
        this.orderService = orderService;
        this.menuRepository = menuRepository;
    }

    @GetMapping("viewOrders")
    public String showOrderList(Model model) {
        List<Orders> listOrders = orderService.listAll();
        model.addAttribute("listOrders", listOrders);
        return "viewOrders";
    }

    @PostMapping("/orders/save")
    public String saveOrder(@ModelAttribute("order") Orders order, RedirectAttributes ra) {
        try {
            orderService.saveOrder(order);
            ra.addFlashAttribute("message", "Order saved successfully.");
            return "redirect:/viewOrders";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Failed to save order: " + e.getMessage());
            return "redirect:/orders";
        }
    }


    @GetMapping("orders")
    public String showOrder(Model model) {
        return "orders";
    }

    @GetMapping("updateStatus")
    public String showUpdateStatus(Model model) {
        return "updateStatus";
    }

    @PostMapping("orders/createWithMenus")
    public String createOrderWithMenus(Integer userId, List<Integer> menuIds, RedirectAttributes ra) {
        try {
            orderService.createOrderWithMenus(userId, menuIds);
            ra.addFlashAttribute("message", "Order created successfully with menus.");
            return "redirect:/orders";
        } catch (Exception e) {
            ra.addFlashAttribute("message", "Failed to create order with menus: " + e.getMessage());
            return "redirect:/orders";
        }
    }

    @GetMapping("/createWithMenus")
    public String showCreateWithMenusPage() {
        return "createWithMenus";
    }


    @GetMapping("/viewOrders/updateStatus/{id}")
    public String showUpdateOrderStatusForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("orderId", id);
        return "updateStatus";
    }



    @PostMapping("updateStatus/{id}")
    public String editStatus(@PathVariable("id") Integer id, @RequestParam("newStatus") String newStatus, Model model, RedirectAttributes ra) {
        try {
            orderService.updateOrderStatus(id, OrderStatus.valueOf(newStatus));
            ra.addFlashAttribute("message", "Order status updated successfully.");
            return "redirect:/viewOrders";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Failed to update order status: " + e.getMessage());
            return "redirect:/viewOrders";
        }
    }

    @GetMapping("/viewOrders/edit/{id}")
    public String showEditOrderForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Orders order = orderService.getOrderById(id).orElseThrow();
            model.addAttribute("order", order);
            model.addAttribute("pageTitle", "Edit Order (ID: " + id + ")");
            return "order_form";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("error", "Order not found with ID: " + id);
            e.printStackTrace();
            return "redirect:/viewOrders";
        }
    }

    @GetMapping("/viewOrders/delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            orderService.deleteOrderById(id);
            ra.addFlashAttribute("message", "Order with ID: " + id + " has been deleted successfully");
            return "redirect:/viewOrders";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("error", "Order not found with ID: " + id);
            e.printStackTrace();
            return "redirect:/viewOrders";
        }
    }



}
