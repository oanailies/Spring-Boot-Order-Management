package com.example.CRUD_Tutorial.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministratorController {

    @GetMapping("/administrator")
    public String showManagementUsersPage() {
        return "administrator";
    }
}