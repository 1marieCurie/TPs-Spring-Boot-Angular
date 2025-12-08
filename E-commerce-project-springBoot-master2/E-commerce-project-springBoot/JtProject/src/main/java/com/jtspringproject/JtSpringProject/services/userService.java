package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.model.User;
import com.jtspringproject.JtSpringProject.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // injecté via constructeur
    // Injection via constructeur
    public userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Ajouter un utilisateur avec mot de passe encodé
    public User addUser(User user) {
        try {
            if (user.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            // rôle par défaut si null
            if (user.getRole() == null) {
                user.setRole("ROLE_USER");
            }
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Add user error: " + e.getMessage());
        }
    }

    // Vérification de login (optionnel si Spring Security gère déjà)
    public User checkLogin(String username, String rawPassword) {
        User user = getUserByUsername(username);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return user;
        }
        return null;
    }

    public boolean checkUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
