package com.geese.codetok.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
// @Table(name = "users") add this once table has been created
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is already taken")
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password; // hash password

    @Email(message = "Please enter a valid email")
    @Column(unique = true)
    private String email;
}
