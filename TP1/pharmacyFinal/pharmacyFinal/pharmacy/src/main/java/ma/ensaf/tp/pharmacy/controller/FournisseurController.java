package ma.ensaf.tp.pharmacy.controller;

import ma.ensaf.tp.pharmacy.dao.FournisseurRepository;
import ma.ensaf.tp.pharmacy.model.Fournisseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
public class FournisseurController {
    @Autowired
    private FournisseurRepository fournisseurRepository;
    
    @GetMapping
    public List<Fournisseur> getAllFournisseurs() {

        return fournisseurRepository.findAll();
    }
    @PostMapping
    public Fournisseur createFournisseur(@RequestBody Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    @PutMapping("/{id}")
    public Fournisseur updateFournisseur(@PathVariable Long id, @RequestBody Fournisseur fournisseur) {
        Fournisseur existing = fournisseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec id " + id));
        existing.setNom(fournisseur.getNom());
        existing.setContact(fournisseur.getContact());
        existing.setAdresse(fournisseur.getAdresse());
        // éventuellement mettre à jour les produits
        return fournisseurRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteFournisseur(@PathVariable Long id) {
        Fournisseur existing = fournisseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec id " + id));
        fournisseurRepository.delete(existing);
    }

}
