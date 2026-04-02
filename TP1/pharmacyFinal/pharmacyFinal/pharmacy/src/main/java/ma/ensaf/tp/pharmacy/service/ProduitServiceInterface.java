package ma.ensaf.tp.pharmacy.service;

import ma.ensaf.tp.pharmacy.model.Produit;
import java.util.List;
import java.util.Optional;

public interface ProduitServiceInterface {
    List<Produit> getAllProduits();
    Optional<Produit> getProduitById(Long id);
    Produit saveProduit(Produit produit);
    Produit updateProduit(Long id, Produit produitDetails);
    void deleteProduit(Long id);
}
