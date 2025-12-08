package com.jtspringproject.JtSpringProject.configuration;

import com.jtspringproject.JtSpringProject.model.User;
import com.jtspringproject.JtSpringProject.services.userService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminCreator implements CommandLineRunner {

    private final userService userService;

    public AdminCreator(userService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        String username = "superadmin"; // ton nouveau nom admin

        if (!userService.checkUserExists(username)) {

            User admin = new User();
            admin.setUsername(username);
            admin.setEmail("superadmin@gmail.com");
            admin.setAddress("Admin Street 123");
            admin.setRole("ROLE_ADMIN");

            // ton hash BCrypt généré :
            admin.setPassword("$2a$10$jSztrohB7DqMbBTxwRCKpeF4hT3kaqRcvdSj1Lzngu.BHqKHV2QKm");

            userService.addUser(admin);

            System.out.println("Nouvel admin ajouté : " + username);
        }
    }
}