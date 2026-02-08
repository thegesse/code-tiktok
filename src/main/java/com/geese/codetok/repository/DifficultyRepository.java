package com.geese.codetok.repository;

import com.geese.codetok.model.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DifficultyRepository extends JpaRepository<Difficulty, Long> {
}
