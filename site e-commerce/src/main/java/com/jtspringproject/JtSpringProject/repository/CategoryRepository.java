package com.jtspringproject.JtSpringProject.repository;

import com.jtspringproject.JtSpringProject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Pas besoin de méthodes supplémentaires pour les opérations basiques
}
