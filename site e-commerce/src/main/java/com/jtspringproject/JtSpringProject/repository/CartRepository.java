package com.jtspringproject.JtSpringProject.repository;

import com.jtspringproject.JtSpringProject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    // Si tu veux récupérer tous les carts d’un utilisateur
    List<Cart> findByCustomerId(Integer customerId);
}
