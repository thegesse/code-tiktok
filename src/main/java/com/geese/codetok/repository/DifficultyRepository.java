package com.geese.codetok.repository;

import com.geese.codetok.model.Difficulty;
import com.geese.codetok.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty, Long> {

    // Returns all levels (Beginner, etc.) associated with a specific user
    List<Difficulty> findByUser(User user);

    // You can also find by the User's ID directly
    List<Difficulty> findByUserId(Long userId);
}
