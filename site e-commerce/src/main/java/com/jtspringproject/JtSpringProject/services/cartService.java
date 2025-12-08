package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.model.Cart;
import com.jtspringproject.JtSpringProject.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart getCart(int id) {
        return cartRepository.findById(id).orElse(null);
    }

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public void updateCart(Cart cart) {
        cartRepository.save(cart); // save() mettra à jour si l'id existe
    }

    public void deleteCart(int id) {
        if(cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
        }
    }

    public List<Cart> getCartByCustomerId(int customerId){
        return cartRepository.findByCustomerId(customerId);
    }
}
