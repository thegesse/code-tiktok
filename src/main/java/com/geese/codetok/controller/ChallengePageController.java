package com.geese.codetok.controller;

import ch.qos.logback.core.model.Model;
import com.geese.codetok.model.CodeProblem;
import com.geese.codetok.service.problems.CodeProblemService;
import com.geese.codetok.service.Ai.PromptTopics;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChallengePageController {
    private final CodeProblemService codeProblemService;
    private final PromptTopics promptTopics;

    public ChallengePageController(CodeProblemService codeProblemService, PromptTopics promptTopics) {
        this.codeProblemService = codeProblemService;
        this.promptTopics = promptTopics;
    }

    @GetMapping("/challenge-page")
    public String showChallengePage(
            @RequestParam(defaultValue = "beginner") String level,
            Model model) {
        String topic;
        if (level.equalsIgnoreCase("intermediate")) {
            topic = promptTopics.getRandomTopicIntermediate();
        } else if (level.equalsIgnoreCase("expert")) {
            topic = promptTopics.getRandomTopicExpert();
        } else {
            topic = promptTopics.getRandomTopicBeginner();
        }
        CodeProblem problem = codeProblemService.createNewProblem(level, topic);
        model.addText("problem");
        model.addText("currentLevel");

        return "challenge";
    }
}
