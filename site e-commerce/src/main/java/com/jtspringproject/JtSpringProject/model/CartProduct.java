package com.jtspringproject.JtSpringProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="CART_PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct {

    @EmbeddedId
    private CartProductKey id;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    // Constructeur pratique
    public CartProduct(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.id = new CartProductKey(cart.getId(), product.getProductId());
    }
    //constructeur perdonnalisé
    public CartProduct(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
        this.id = new CartProductKey(cart.getId(), product.getProductId());
    }


}
