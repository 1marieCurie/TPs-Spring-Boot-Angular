package com.jtspringproject.JtSpringProject.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartProductId implements Serializable {

    @Column(name = "cart_id")
    private Integer cartId;

    @Column(name = "product_id")
    private Integer productId;

}
