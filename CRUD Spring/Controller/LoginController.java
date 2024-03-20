package com.example.CRUD_Tutorial.Controller;

import com.example.CRUD_Tutorial.Entity.User;
import com.example.CRUD_Tutorial.Entity.UserRole;
import com.example.CRUD_Tutorial.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    public LoginController(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(User user, RedirectAttributes ra) {
        User existingUser = userService.findByUsername(user.getUsername());

        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            if (existingUser.getRole() == UserRole.ADMINISTRATOR) {
                return "redirect:/administrator";
            } else if (existingUser.getRole() == UserRole.EMPLOYEE) {
                return "redirect:/employee";
            }
        }

        ra.addFlashAttribute("error", "Invalid username or password");
        return "redirect:/login";
    }

}
