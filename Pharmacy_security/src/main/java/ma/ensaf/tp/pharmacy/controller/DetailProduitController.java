package ma.ensaf.tp.pharmacy.controller;

import ma.ensaf.tp.pharmacy.model.DetailProduit;
import ma.ensaf.tp.pharmacy.model.Medicament;
import ma.ensaf.tp.pharmacy.dao.DetailProduitRepository;
import ma.ensaf.tp.pharmacy.dao.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/details")
public class DetailProduitController {

    @Autowired
    private DetailProduitRepository detailRepository;

    @Autowired
    private MedicamentRepository medicamentRepository;

    @GetMapping("/medicament/{medId}")
    @PreAuthorize("hasAnyRole('ADMIN','PHARMACIEN')")
    public String showDetailForm(@PathVariable Long medId, Model model) {
        Medicament medicament = medicamentRepository.findById(medId).orElse(null);

        // Si le médicament n'existe pas, on affiche un formulaire vierge avec une liste de tous les médicaments
        if (medicament == null) {
            model.addAttribute("detailProduit", new DetailProduit());
            model.addAttribute("medicaments", medicamentRepository.findAll());
            return "detail-form"; // formulaire pour ajouter un nouveau détail
        }

        DetailProduit detail = medicament.getDetailProduit();
        if (detail == null) {
            detail = new DetailProduit();
        }

        model.addAttribute("medicament", medicament);
        model.addAttribute("detailProduit", detail);
        return "detail-form";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN','PHARMACIEN')")
    public String saveDetail(@ModelAttribute DetailProduit detailProduit, @RequestParam Long medicamentId) {
        Medicament medicament = medicamentRepository.findById(medicamentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Medicament Id:" + medicamentId));

        detailProduit.setMedicament(medicament);
        detailRepository.save(detailProduit);
        medicament.setDetailProduit(detailProduit);
        medicamentRepository.save(medicament);

        return "redirect:/medicaments/detail/" + medicamentId;
    }


}

