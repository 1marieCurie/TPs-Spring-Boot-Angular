package ma.ensaf.tp.pharmacy.controller;

import ma.ensaf.tp.pharmacy.model.Medicament;
import ma.ensaf.tp.pharmacy.dao.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicaments")
public class MedicamentController {

    @Autowired
    private MedicamentRepository medicamentRepository;

    @GetMapping
    public String listMedicaments(Model model) {
        model.addAttribute("medicaments", medicamentRepository.findAll());
        return "medicaments";
    }

    // Ajouter
    @GetMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'PHARMACIEN')")
    public String showAddForm(Model model) {
        model.addAttribute("medicament", new Medicament());
        return "medicament-form";
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'PHARMACIEN')")
    public String saveMedicament(@ModelAttribute Medicament medicament) {
        medicamentRepository.save(medicament);
        return "redirect:/medicaments";
    }

    // Modifier
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PHARMACIEN')")
    public String showEditForm(@PathVariable Long id, Model model) {
        Medicament medicament = medicamentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid medicament Id:" + id));
        model.addAttribute("medicament", medicament);
        return "medicament-form";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PHARMACIEN')")
    public String updateMedicament(@PathVariable Long id, @ModelAttribute Medicament formMedicament) {
        Medicament medicament = medicamentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid medicament Id:" + id));

        // Mise à jour uniquement des champs du formulaire
        medicament.setNom(formMedicament.getNom());
        medicament.setDescription(formMedicament.getDescription());
        medicament.setPrix(formMedicament.getPrix());
        medicament.setCodeBarre(formMedicament.getCodeBarre());
        medicament.setDateExpiration(formMedicament.getDateExpiration());

        medicamentRepository.save(medicament);
        return "redirect:/medicaments";
    }

    // Supprimer
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteMedicament(@PathVariable Long id) {
        medicamentRepository.deleteById(id);
        return "redirect:/medicaments";
    }

    @GetMapping("/details/{id}")
    public String viewDetail(@PathVariable Long id, Model model) {
        Medicament medicament = medicamentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid medicament Id:" + id));

        // On récupère directement le détail lié au produit
        model.addAttribute("detailProduit", medicament.getDetailProduit());
        model.addAttribute("medicament", medicament); // utile pour affichage nom produit
        return "detail-view"; // template pour un seul détail
    }
}
