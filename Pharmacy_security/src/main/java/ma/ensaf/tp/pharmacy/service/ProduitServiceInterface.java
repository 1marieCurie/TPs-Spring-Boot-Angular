package ma.ensaf.tp.pharmacy.service;

import ma.ensaf.tp.pharmacy.model.Medicament;
import java.util.List;
import java.util.Optional;

public interface ProduitServiceInterface {
    List<Medicament> getAllProduits();
    Optional<Medicament> getProduitById(Long id);
    Medicament saveProduit(Medicament medicament);
    Medicament updateProduit(Long id, Medicament produitDetails);
    void deleteProduit(Long id);
}
