package ma.ensaf.tp.pharmacy.dao;

import ma.ensaf.tp.pharmacy.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {}

