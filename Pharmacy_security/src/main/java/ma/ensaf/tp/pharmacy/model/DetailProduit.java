package ma.ensaf.tp.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detail_produit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fabricant;
    private String paysOrigine;
    private String composition;
    private String dosage;
    private String indications;
    
 // OneToOne inversé vers Produit 
    @OneToOne(mappedBy = "detailProduit", cascade = CascadeType.ALL)
    private Medicament medicament;
}
