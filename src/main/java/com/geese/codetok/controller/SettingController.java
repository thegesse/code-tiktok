package com.geese.codetok.controller;


import com.geese.codetok.model.User;
import com.geese.codetok.service.settings.SettingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/settings")
public class SettingController {
    private final SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @PostMapping("/update-difficulty")
    public String updateDifficulty(@RequestParam Long difficultyId, HttpSession session, RedirectAttributes ra) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        settingService.changeUserDifficulty(user, difficultyId);
        session.setAttribute("user", user);
        ra.addFlashAttribute("message", "Difficulty updated!");

        return "redirect:/settings";
    }

    @PostMapping("/update-username")
    public String updateUsername(@RequestParam String newUsername, HttpSession session, RedirectAttributes ra) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        settingService.changeUsername(user, newUsername);

        session.setAttribute("user", user);
        ra.addFlashAttribute("message", "Username changed!");

        return "redirect:/settings";
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam String oldPass, @RequestParam String newPass, @RequestParam String confirmPass,HttpSession session, RedirectAttributes ra) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        if (!newPass.equals(confirmPass)) {
            ra.addFlashAttribute("error", "New passwords do not match!");
            return "redirect:/settings";
        }
        try {
            settingService.changePassword(user, oldPass, newPass);
            session.setAttribute("user", user);
            ra.addFlashAttribute("message", "Password updated successfully! 🔥");

        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/settings";
    }

}
