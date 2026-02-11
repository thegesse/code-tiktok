package com.geese.codetok.controller;

import org.springframework.ui.Model;
import com.geese.codetok.service.auth.AuthService;
import com.geese.codetok.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, @RequestParam String level) {
        authService.registerNewUser(user, level);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        User user = authService.loginUser(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/dashBoard";
        } else {
            model.addAttribute("error");
            return "login";
        }
    }
}
