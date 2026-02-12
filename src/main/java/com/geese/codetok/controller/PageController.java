package com.geese.codetok.controller;

import com.geese.codetok.model.CodeProblem;
import com.geese.codetok.model.User;
import com.geese.codetok.service.problems.CodeProblemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {
    private final CodeProblemService codeProblemService;

    public PageController(CodeProblemService codeProblemService) {
        this.codeProblemService = codeProblemService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/dashBoard")
    public String showDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        List<CodeProblem> recent = codeProblemService.getRecentPuzzlesForUser(user);

        model.addAttribute("user", user);
        model.addAttribute("puzzles", recent);
        return "dashBoard";
    }
}
