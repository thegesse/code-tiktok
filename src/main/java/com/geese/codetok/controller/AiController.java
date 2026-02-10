package com.geese.codetok.controller;

import com.geese.codetok.model.CodeProblem;
import com.geese.codetok.repository.CodeProblemRepository;
import com.geese.codetok.service.Ai.AiService;
import com.geese.codetok.service.Ai.PromptTopics;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/api/challenge")
public class AiController {
    private final AiService aiService;
    private final PromptTopics promptTopics;

    public AiController(AiService aiService, PromptTopics promptTopics) {
        this.aiService = aiService;
        this.promptTopics = promptTopics;
    }

    @GetMapping("/beginner")
    public String generateProblemBeginner() {
        String topic = promptTopics.getRandomTopicBeginner();
        return aiService.beginnerCodeProblem(topic);
    }

    @GetMapping("/intermediate")
    public String generateProblemIntermediate() {
        String topic = promptTopics.getRandomTopicIntermediate();
        return aiService.intermediateCodeProblem(topic);
    }

    @GetMapping("/expert")
    public String generateProblemExpert() {
        String topic = promptTopics.getRandomTopicExpert();
        return aiService.expertCodeProblem(topic);
    }
}
