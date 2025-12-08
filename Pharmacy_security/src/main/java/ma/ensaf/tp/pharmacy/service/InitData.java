package ma.ensaf.tp.pharmacy.service;

import ma.ensaf.tp.pharmacy.model.Role;
import ma.ensaf.tp.pharmacy.model.User;
import ma.ensaf.tp.pharmacy.dao.RoleRepository;
import ma.ensaf.tp.pharmacy.dao.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class InitData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public InitData(UserRepository userRepository,
                    RoleRepository roleRepository,
                    PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // ---- Création des rôles ----
        Role roleAdmin = createRoleIfNotExists("ROLE_ADMIN");
        Role rolePharmacien = createRoleIfNotExists("ROLE_PHARMACIEN");
        Role roleCaissier = createRoleIfNotExists("ROLE_CAISSIER");
        Role roleClient = createRoleIfNotExists("ROLE_CLIENT");

        // ---- Création des utilisateurs ----
        createUserIfNotExists("admin", "admin123", roleAdmin);
        createUserIfNotExists("pharma", "pharma123", rolePharmacien);
        createUserIfNotExists("caisse", "caisse123", roleCaissier);
        createUserIfNotExists("client", "client123", roleClient);

        System.out.println("Initialisation terminée !");
    }

    private Role createRoleIfNotExists(String nom) {
        Role role = roleRepository.findByNom(nom);
        if (role == null) {
            role = new Role();
            role.setNom(nom);
            roleRepository.save(role);
            System.out.println("Rôle créé : " + nom);
        }
        return role;
    }

    private void createUserIfNotExists(String username, String password, Role role) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(Arrays.asList(role));
            userRepository.save(user);
            System.out.println("Utilisateur créé : " + username);
        }
    }
}
