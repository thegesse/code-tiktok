package com.geese.codetok.controller;

import com.geese.codetok.model.CodeProblem;
import com.geese.codetok.model.User;
import com.geese.codetok.service.problems.CodeProblemService;
import com.geese.codetok.service.Ai.PromptTopics;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // CORRECT IMPORT
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChallengePageController {
    private final CodeProblemService codeProblemService;
    private final PromptTopics promptTopics;

    public ChallengePageController(CodeProblemService codeProblemService, PromptTopics promptTopics) {
        this.codeProblemService = codeProblemService;
        this.promptTopics = promptTopics;
    }

    @GetMapping("/play")
    public String showChallengePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        String level = user.getDifficulty().getLevel();
        String topic;

        if (level.equalsIgnoreCase("intermediate")) {
            topic = promptTopics.getRandomTopicIntermediate();
        } else if (level.equalsIgnoreCase("expert")) {
            topic = promptTopics.getRandomTopicExpert();
        } else {
            topic = promptTopics.getRandomTopicBeginner();
        }
        CodeProblem problem = codeProblemService.createNewProblem(level, topic);

        model.addAttribute("problem", problem);
        model.addAttribute("currentLevel", level);

        return "play";
    }
}