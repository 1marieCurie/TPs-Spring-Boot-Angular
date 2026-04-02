package ma.ensaf.tp.pharmacy.service;

import ma.ensaf.tp.pharmacy.model.*;
import ma.ensaf.tp.pharmacy.dao.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService implements ProduitServiceInterface {

    @Autowired
    private ProduitRepository produitRepository;
    
    @PersistenceContext
    private EntityManager entityManager; // ← injection de l'EntityManager

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }

    @Override
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Produit updateProduit(Long id, Produit produitDetails) {
        return produitRepository.findById(id).map(produit -> {
            produit.setNom(produitDetails.getNom());
            produit.setDescription(produitDetails.getDescription());
            produit.setPrix(produitDetails.getPrix());
            produit.setCodeBarre(produitDetails.getCodeBarre());
            produit.setDateExpiration(produitDetails.getDateExpiration());

            // Mises à jour sécurisées des relations ManyToOne
            if (produitDetails.getCategorie() != null && produitDetails.getCategorie().getId() != null) {
            	// récupère la référence de l’entité existante
            	Categorie catRef = entityManager.getReference(Categorie.class, produitDetails.getCategorie().getId());
                produit.setCategorie(catRef);
            }

            if (produitDetails.getFournisseur() != null && produitDetails.getFournisseur().getId() != null) {
            	Fournisseur fouRef = entityManager.getReference(Fournisseur.class, produitDetails.getFournisseur().getId());
                produit.setFournisseur(fouRef);
            }

            // si fourni, met à jour ou remplace
            if (produitDetails.getDetailProduit() != null) {
                produit.setDetailProduit(produitDetails.getDetailProduit());
            }

            // emplacer complètement l’ancien ensemble de tags par les nouveaux reçus
            if (produitDetails.getTags() != null) {
                produit.getTags().clear(); // vider le set
                produit.getTags().addAll(produitDetails.getTags());
            }

            return produitRepository.save(produit);
        }).orElseThrow(() -> new RuntimeException("Produit not found with id " + id));
    }


    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
