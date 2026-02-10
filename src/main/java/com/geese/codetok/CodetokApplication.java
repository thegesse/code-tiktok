package com.geese.codetok;

import com.geese.codetok.model.Difficulty;
import com.geese.codetok.model.User;
import com.geese.codetok.repository.DifficultyRepository;
import com.geese.codetok.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CodetokApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodetokApplication.class, args);
    }

    // running db in memory so this is needed will delete once it is running locally or on a server
    @Bean
    CommandLineRunner init(DifficultyRepository diffRepo, UserRepository userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if (diffRepo.count() == 0) {
                diffRepo.save(new Difficulty(null, "beginner"));
                diffRepo.save(new Difficulty(null, "intermediate"));
                diffRepo.save(new Difficulty(null, "expert"));
                System.out.println("Database Seeded");
            }

            if (userRepo.count() == 0) {
                Difficulty beginner = diffRepo.findAll().stream()
                        .filter(d -> "beginner".equals(d.getLevel()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Difficulty not found"));

                User testuser = User.builder()
                        .username("test")
                        .difficulty(beginner)
                        .password(passwordEncoder.encode("1234"))
                        .email("test@yahoo.com")
                        .build();
                userRepo.save(testuser);
                System.out.println("user created");
            }
        };
    }


}
