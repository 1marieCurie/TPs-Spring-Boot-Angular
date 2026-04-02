package ma.ensaf.tp.pharmacy.controller;

import ma.ensaf.tp.pharmacy.model.Produit;
import ma.ensaf.tp.pharmacy.dao.CategorieRepository;
import ma.ensaf.tp.pharmacy.dao.FournisseurRepository;
import ma.ensaf.tp.pharmacy.dao.TagRepository;
import ma.ensaf.tp.pharmacy.service.ProduitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProduitViewController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/produits")
    public String showProduits(Model model) {
        model.addAttribute("produits", produitService.getAllProduits());
        model.addAttribute("categories", categorieRepository.findAll());
        model.addAttribute("fournisseurs", fournisseurRepository.findAll());
        model.addAttribute("tags", tagRepository.findAll());
        return "produits"; // src/main/resources/templates/produits.html
    }
}