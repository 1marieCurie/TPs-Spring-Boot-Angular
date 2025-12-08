package ma.ensaf.tp.pharmacy.controller;

import ma.ensaf.tp.pharmacy.dao.CategorieRepository;
import ma.ensaf.tp.pharmacy.model.Categorie;
import ma.ensaf.tp.pharmacy.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping
    public List<Categorie> getAllCategories() {

        return categorieRepository.findAll();

    }
    @PostMapping
    public Categorie createCategory(@RequestBody Categorie category) {
        return categorieRepository.save(category);
    }
    @PutMapping("/{id}")
    public Categorie updateCategory(@PathVariable Long id, @RequestBody Categorie category) {
        Categorie existingCategory = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie non trouvée avec id " + id));

        existingCategory.setNom(category.getNom());
        // tu peux mettre à jour d'autres champs si nécessaire

        return categorieRepository.save(existingCategory);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        Categorie existingCategory = categorieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categorie non trouvée avec id " + id));

        categorieRepository.delete(existingCategory);
    }

}
