package com.jtspringproject.JtSpringProject.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Admin123"; // mot de passe que tu choisis
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Mot de passe encodé : " + encodedPassword);
    }
}
