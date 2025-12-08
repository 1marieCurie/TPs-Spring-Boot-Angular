package ma.ensaf.tp.pharmacy.controller;

import ma.ensaf.tp.pharmacy.model.Categorie;
import ma.ensaf.tp.pharmacy.dao.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategorieController {

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categorieRepository.findAll());
        return "categories";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddForm(Model model) {
        model.addAttribute("categorie", new Categorie());
        return "categorie-form";
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveCategorie(@ModelAttribute Categorie categorie) {
        categorieRepository.save(categorie);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEditForm(@PathVariable Long id, Model model) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Categorie Id:" + id));
        model.addAttribute("categorie", categorie);
        return "categorie-form";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateCategorie(@PathVariable Long id, @ModelAttribute Categorie formCategorie) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Categorie Id:" + id));
        categorie.setNom(formCategorie.getNom());
        categorie.setDescription(formCategorie.getDescription());
        categorieRepository.save(categorie);
        return "redirect:/categories";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCategorie(@PathVariable Long id) {
        categorieRepository.deleteById(id);
        return "redirect:/categories";
    }
}
