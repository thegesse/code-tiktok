package com.geese.codetok.repository;

import com.geese.codetok.model.Difficulty;
import com.geese.codetok.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty, Long> {
    List<Difficulty> findByUser(User user);
    List<Difficulty> findByUserId(Long userId);
    Optional<Difficulty> findByLevel(String level);
}
