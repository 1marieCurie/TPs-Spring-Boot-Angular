package ma.ensaf.tp.pharmacy.dao;

import ma.ensaf.tp.pharmacy.model.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Medicament, Long> {}
