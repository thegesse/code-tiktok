package com.geese.codetok.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "difficulty")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Difficulty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Difficulty level cannot be blank")
    @Column(unique = true)
    private String level;
}