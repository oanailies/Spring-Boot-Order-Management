package com.example.CRUD_Tutorial.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @GetMapping("/employee")
    public String showManagementUsersPage() {
        return "employee"; // Returnează numele paginii HTML
    }
}
