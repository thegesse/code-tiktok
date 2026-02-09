package com.geese.codetok.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "code_problems")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CodeProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "difficulty_id")
    private Difficulty difficulty;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String code;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String answer;
}
