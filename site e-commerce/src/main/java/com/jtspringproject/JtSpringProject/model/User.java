package com.jtspringproject.JtSpringProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMER") // la table existante dans MySQL
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String email;
    private String address;
    private String role;

    @OneToMany(mappedBy = "customer")
    private List<Cart> carts;

    @OneToMany(mappedBy = "customer")
    private List<Product> products;
}
