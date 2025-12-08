package ma.ensaf.tp.pharmacy.controller;

import ma.ensaf.tp.pharmacy.model.Produit;
import ma.ensaf.tp.pharmacy.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detailProduits")
public class ProduitDetailsController {
    @Autowired   // Trouve un bean de type ProduitService et injecte-le ici automatiquement
    private ProduitService produitService;

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitDetailsById(@PathVariable Long id) {
        return produitService.getProduitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
