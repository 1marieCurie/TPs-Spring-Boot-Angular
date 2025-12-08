package ma.ensaf.tp.pharmacy.dao;

import ma.ensaf.tp.pharmacy.model.DetailProduit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailProduitRepository extends JpaRepository<DetailProduit, Long> {}

