package com.geese.codetok.repository;

import com.geese.codetok.model.CodeProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CodeProblemRepository extends JpaRepository<CodeProblem, Long>{
}
