package ma.ensaf.tp.pharmacy.controller;

import ma.ensaf.tp.pharmacy.model.Tag;
import ma.ensaf.tp.pharmacy.dao.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tags")
@PreAuthorize("hasRole('ADMIN')")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    // Lister tous les tags
    @GetMapping
    public String listTags(Model model) {
        model.addAttribute("tags", tagRepository.findAll());
        return "tags";
    }

    // Formulaire d'ajout
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "tag-form";
    }

    // Sauvegarder un nouveau tag
    @PostMapping("/")
    public String saveTag(@ModelAttribute Tag tag) {
        tagRepository.save(tag);
        return "redirect:/tags";
    }

    // Formulaire d'édition
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Tag Id:" + id));
        model.addAttribute("tag", tag);
        return "tag-form";
    }

    // Mettre à jour un tag
    @PutMapping("/{id}")
    public String updateTag(@PathVariable Long id, @ModelAttribute Tag formTag) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Tag Id:" + id));

        // Mise à jour uniquement des champs modifiables
        tag.setLibelle(formTag.getLibelle());

        tagRepository.save(tag);
        return "redirect:/tags";
    }

    // Supprimer un tag
    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable Long id) {
        tagRepository.deleteById(id);
        return "redirect:/tags";
    }

    // Afficher les détails d'un tag
    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable Long id, Model model) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Tag Id:" + id));
        model.addAttribute("tag", tag);
        return "tag-detail";
    }
}
