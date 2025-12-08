package com.jtspringproject.JtSpringProject.repository;

import com.jtspringproject.JtSpringProject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Tu n'as rien à ajouter pour les méthodes CRUD de base.
}