package com.geese.codetok.service;

import com.geese.codetok.model.Difficulty;
import com.geese.codetok.repository.DifficultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DifficultyService {
    private final DifficultyRepository difficultyRepository;
    public DifficultyService(DifficultyRepository difficultyRepository) {
        this.difficultyRepository = difficultyRepository;
    }

    public List<Difficulty> getAllDifficulty() {
        return difficultyRepository.findAll();
    }

    public Difficulty getDifficultyById(Long id) {
        return difficultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Difficulty level not found"));
    }
}
