package com.geese.codetok.service.problems;

import com.geese.codetok.model.CodeProblem;
import com.geese.codetok.model.Difficulty;
import com.geese.codetok.model.User;
import com.geese.codetok.repository.CodeProblemRepository;
import com.geese.codetok.repository.DifficultyRepository;
import com.geese.codetok.service.Ai.AiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeProblemService {
    private final AiService aiService;
    private final DifficultyRepository difficultyRepository;
    private final CodeProblemRepository codeProblemRepository;

    public CodeProblemService(AiService aiService, DifficultyRepository difficultyRepository, CodeProblemRepository codeProblemRepository) {
        this.aiService = aiService;
        this.difficultyRepository = difficultyRepository;
        this.codeProblemRepository = codeProblemRepository;
    }

    public CodeProblem createNewProblem(String level, String topic) {
        String aiResponse;
        if(level.equalsIgnoreCase("beginner")){
            aiResponse = aiService.beginnerCodeProblem(topic);
        }else if(level.equalsIgnoreCase("intermediate")){
            aiResponse = aiService.intermediateCodeProblem(topic);
        }else {
            aiResponse = aiService.expertCodeProblem(topic);
        }

        Difficulty difficulty = difficultyRepository.findByLevel(level)
                .orElseThrow(() -> new RuntimeException("Difficulty level '" + level + "' not found"));

        CodeProblem newProblem = new CodeProblem();
        newProblem.setDifficulty(difficulty);
        newProblem.setCode(aiResponse);
        newProblem.setAnswer("Check the end of the code snippet for the fix.");

        return codeProblemRepository.save(newProblem);
    }
    public CodeProblem findById(Long id) {
        return codeProblemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Problem not found with id: " + id));
    }

    public List<CodeProblem> getRecentPuzzlesForUser(User user) {

        return codeProblemRepository.findTop5ByOrderByIdDesc(user.getId());
    }
}
