package com.geese.codetok.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Column(unique = true, nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "difficulty_id")
    private Difficulty difficulty;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password; //reminder to hash password

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;


    @Builder.Default
    private java.time.LocalDateTime joinDate = java.time.LocalDateTime.now();
    @PrePersist
    protected void onCreate() {
        if (this.joinDate == null) {
            this.joinDate = java.time.LocalDateTime.now();
        }
    }

    @Builder.Default
    private Integer points = 0;
}
