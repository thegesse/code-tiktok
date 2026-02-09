package com.geese.codetok;

import com.geese.codetok.model.Difficulty;
import com.geese.codetok.repository.DifficultyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CodetokApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodetokApplication.class, args);
    }

    @Bean
    CommandLineRunner init(DifficultyRepository repository) {
        return args -> {
            // This checks if the table is empty before adding
            if (repository.count() == 0) {
                repository.save(new Difficulty(null, "beginner"));
                repository.save(new Difficulty(null, "intermediate"));
                repository.save(new Difficulty(null, "expert"));
                System.out.println("✅ Database Seeded: Levels are ready!");
            }
        };
    }
}
