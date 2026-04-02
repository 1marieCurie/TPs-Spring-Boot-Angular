package ma.ensaf.tp.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tag {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String libelle;
    
    // ManyToMany inversé vers Produit
    @ManyToMany(mappedBy = "tags")
    private Set<Medicament> medicaments = new HashSet<>();
}
