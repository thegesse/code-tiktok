package com.geese.codetok.repository;

import com.geese.codetok.model.CodeProblem;
import com.geese.codetok.model.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeProblemRepository extends JpaRepository<CodeProblem, Long> {

    List<CodeProblem> findByDifficulty(Difficulty difficulty);
    List<CodeProblem> findByDifficultyId(Long difficultyId);
    @Query(value = "SELECT * FROM code_problems WHERE difficulty_id = ?1 ORDER BY RAND() LIMIT 1", nativeQuery = true)
    CodeProblem findRandomByDifficulty(Long difficultyId);

    List<CodeProblem> findTop5ByOrderByIdDesc(Long userId);
}