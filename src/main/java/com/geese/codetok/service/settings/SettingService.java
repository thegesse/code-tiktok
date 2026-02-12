package com.geese.codetok.service.settings;


import com.geese.codetok.model.Difficulty;
import com.geese.codetok.model.User;
import com.geese.codetok.repository.DifficultyRepository;
import com.geese.codetok.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {
    private final UserRepository userRepository;
    private final DifficultyRepository difficultyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SettingService(UserRepository userRepository, DifficultyRepository difficultyRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.difficultyRepository = difficultyRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void changeUserDifficulty(User user, Long difficultyId) {
        Difficulty newDifficulty = difficultyRepository.findById(difficultyId)
                .orElseThrow(() -> new RuntimeException("Difficulty not found"));

        user.setDifficulty(newDifficulty);
        userRepository.save(user);
    }

    public void changeUsername(User user, String username) {
        user.setUsername(username);
        userRepository.save(user);
    }

    public void changePassword(User user, String oldPassword, String newPassword) {
        if (!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("The current password you entered is incorrect.");
        }

        String hashedNewPassword = bCryptPasswordEncoder.encode(newPassword);
        user.setPassword(hashedNewPassword);
        userRepository.save(user);
    }

    public List<Difficulty> getAllDifficulties() {
        return difficultyRepository.findAll();
    }
}
