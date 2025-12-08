package ma.ensaf.tp.pharmacy.controller;

import ma.ensaf.tp.pharmacy.model.Medicament;
import ma.ensaf.tp.pharmacy.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitRestController {

    @Autowired   // Trouve un bean de type ProduitService et injecte-le ici automatiquement
    private ProduitService produitService;

    @GetMapping
    public List<Medicament> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicament> getProduitById(@PathVariable Long id) {
        return produitService.getProduitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medicament createProduit(@RequestBody Medicament medicament) {
        return produitService.saveProduit(medicament);
    }

    @PutMapping("/{id}")
    public Medicament updateProduit(@PathVariable Long id, @RequestBody Medicament produitDetails) {
        return produitService.updateProduit(id, produitDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }
}
