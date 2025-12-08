package ma.ensaf.tp.pharmacy.controller;

import ma.ensaf.tp.pharmacy.model.User;
import ma.ensaf.tp.pharmacy.model.Role;
import ma.ensaf.tp.pharmacy.dao.UserRepository;
import ma.ensaf.tp.pharmacy.dao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Liste des utilisateurs
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }

    // Formulaire création
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/user-form";
    }

    // Sauvegarder nouvel utilisateur
    @PostMapping("/")
    public String saveUser(@ModelAttribute User user, @RequestParam(required = false) List<Long> roleIds) {
        if (roleIds != null) {
            List<Role> roles = roleRepository.findAllById(roleIds);
            user.setRoles(roles);
        }
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    // Formulaire édition
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/user-form";
    }

    // Mettre à jour utilisateur
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User formUser,
                             @RequestParam(required = false) List<Long> roleIds) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        user.setUsername(formUser.getUsername());

        // Si le mot de passe est rempli, le mettre à jour
        if (formUser.getPassword() != null && !formUser.getPassword().isEmpty()) {
            user.setPassword(formUser.getPassword());
        }

        if (roleIds != null) {
            List<Role> roles = roleRepository.findAllById(roleIds);
            user.setRoles(roles);
        }

        userRepository.save(user);
        return "redirect:/admin/users";
    }

    // Supprimer utilisateur
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }
}
