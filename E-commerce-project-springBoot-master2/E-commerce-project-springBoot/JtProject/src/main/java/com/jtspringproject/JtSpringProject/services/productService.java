package com.jtspringproject.JtSpringProject.services;

import java.util.List;

import com.jtspringproject.JtSpringProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.jtspringproject.JtSpringProject.model.Product;

@Service
public class productService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(int id, Product product) {
        product.setProductId(id); // <-- utiliser le setter correct
        return productRepository.save(product);
    }


    public boolean deleteProduct(int id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
