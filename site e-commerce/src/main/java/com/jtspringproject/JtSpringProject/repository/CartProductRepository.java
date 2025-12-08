package com.jtspringproject.JtSpringProject.repository;

import com.jtspringproject.JtSpringProject.model.CartProduct;
import com.jtspringproject.JtSpringProject.model.CartProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, CartProductId> {
}
