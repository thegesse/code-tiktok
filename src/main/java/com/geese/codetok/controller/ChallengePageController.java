package com.geese.codetok.controller;

import com.geese.codetok.model.CodeProblem;
import com.geese.codetok.model.User;
import com.geese.codetok.repository.CodeProblemRepository;
import com.geese.codetok.service.Ai.AiService;
import com.geese.codetok.service.problems.CodeProblemService;
import com.geese.codetok.service.Ai.PromptTopics;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // CORRECT IMPORT
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ChallengePageController {
    private final CodeProblemService codeProblemService;
    private final PromptTopics promptTopics;
    private final AiService aiService;

    public ChallengePageController(CodeProblemService codeProblemService, PromptTopics promptTopics, AiService aiService) {
        this.codeProblemService = codeProblemService;
        this.promptTopics = promptTopics;
        this.aiService = aiService;
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

    @PostMapping("/check-answer")
    public String checkAnswer(@RequestParam Long problemId, @RequestParam String userAnswer, RedirectAttributes ra) {
        CodeProblem problem = codeProblemService.findById(problemId);
        String result = aiService.verifyAnswers(problem.getCode(), userAnswer);

        if (result.toUpperCase().replace(":", "").contains("PASS")) {
            ra.addFlashAttribute("message", "PASS");
        } else {
            ra.addFlashAttribute("message", "FAIL");
            System.out.println("AI Verdict was: " + result);
        }
        return "redirect:/play";
    }
}