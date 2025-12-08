package ma.ensaf.tp.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity   // Indique que cette classe est une entité JPA et correspond à une table SQL
@Table(name = "produit")
@Data // génère getters, setters, toString, equals, hashCode
@NoArgsConstructor // constructeur vide obligatoire pour JPA
@AllArgsConstructor // constructeur avec tous les champs
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produit {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // clé primaire auto-incrémentée
    private Long id;

    private String nom;

    @Column(length = 2000)
    private String description;

    private Double prix;

    @Column(name = "code_barre")
    private String codeBarre;

    @Column(name = "date_expiration")
    private LocalDate dateExpiration;

    // ManyToOne vers Categorie (plusieurs produit ayant méme categorie)
    @ManyToOne(fetch = FetchType.LAZY)  // ne charge la relation que si on l’utilise (optimisation)
    @JoinColumn(name = "categorie_id")  // Nom de la colonne clé étrangère en base
    @JsonIgnoreProperties({"produits", "hibernateLazyInitializer", "handler"})
    private Categorie categorie;

    // ManyToOne vers Fournisseur
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur_id")
    @JsonIgnoreProperties({"produits", "hibernateLazyInitializer", "handler"})
    private Fournisseur fournisseur;

    // OneToOne detail produit
    // cascade = CascadeType.ALL -> les opérations persist, remove sur Produit s’appliquent aussi à DetailProduit
    // orphanRemoval = true -> supprime automatiquement le détail si on le retire du produit
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) 
    @JoinColumn(name = "detail_id")
    @JsonIgnoreProperties({"produits", "hibernateLazyInitializer", "handler"})
    private DetailProduit detailProduit;

    // ManyToMany tags
    @ManyToMany
    @JoinTable(   // Définition de la table de jointure et des colonnes
      name = "produit_tag",
      joinColumns = @JoinColumn(name = "produit_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnoreProperties("produits")
    private Set<Tag> tags = new HashSet<>();

}

