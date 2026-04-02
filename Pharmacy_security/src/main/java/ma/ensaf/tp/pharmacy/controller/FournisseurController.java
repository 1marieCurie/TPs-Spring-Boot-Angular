package ma.ensaf.tp.pharmacy.controller;

import ma.ensaf.tp.pharmacy.model.Fournisseur;
import ma.ensaf.tp.pharmacy.dao.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @GetMapping
    public String listFournisseurs(Model model) {
        model.addAttribute("fournisseurs", fournisseurRepository.findAll());
        return "fournisseurs";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddForm(Model model) {
        model.addAttribute("fournisseur", new Fournisseur());
        return "fournisseur-form";
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveFournisseur(@ModelAttribute Fournisseur fournisseur) {
        fournisseurRepository.save(fournisseur);
        return "redirect:/fournisseurs";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEditForm(@PathVariable Long id, Model model) {
        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Fournisseur Id:" + id));
        model.addAttribute("fournisseur", fournisseur);
        return "fournisseur-form";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateFournisseur(@PathVariable Long id, @ModelAttribute Fournisseur formFournisseur) {
        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Fournisseur Id:" + id));
        fournisseur.setNom(formFournisseur.getNom());
        fournisseur.setDescription(formFournisseur.getDescription());
        fournisseurRepository.save(fournisseur);
        return "redirect:/fournisseurs";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteFournisseur(@PathVariable Long id) {
        fournisseurRepository.deleteById(id);
        return "redirect:/fournisseurs";
    }
}
