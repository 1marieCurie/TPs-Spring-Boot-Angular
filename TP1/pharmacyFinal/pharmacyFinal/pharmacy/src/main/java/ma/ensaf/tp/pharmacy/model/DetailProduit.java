package ma.ensaf.tp.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detail_produit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetailProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fabricant;

    @Column(name = "pays_origine")
    private String paysOrigine;

    private String composition;

    private String dosage;

    private String indications;

    // OneToOne inversé vers Produit
    @OneToOne(mappedBy = "detailProduit")
    @JsonIgnoreProperties("detailProduit")
    private Produit produit;
}
