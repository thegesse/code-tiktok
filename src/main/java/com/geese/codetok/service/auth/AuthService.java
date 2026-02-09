package com.geese.codetok.service.auth;

import com.geese.codetok.model.Difficulty;
import com.geese.codetok.repository.DifficultyRepository;
import com.geese.codetok.repository.UserRepository;
import com.geese.codetok.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DifficultyRepository difficultyRepository;
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, DifficultyRepository difficultyRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.difficultyRepository = difficultyRepository;
    }

    public User registerNewUser(User user, String level) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username is already in use");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Difficulty difficulty = difficultyRepository.findByLevel(level.toLowerCase())
                .orElseThrow(() -> new RuntimeException("Difficulty level not found: " + level));

        user.setDifficulty(difficulty);
        return userRepository.save(user);
    }
    public User loginUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> bCryptPasswordEncoder.matches(password, user.getPassword()))
                .orElse(null);
    }
}
