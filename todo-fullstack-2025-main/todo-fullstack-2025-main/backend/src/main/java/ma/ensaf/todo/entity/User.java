package ma.ensaf.todo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Génère getters, setters, toString, equals, hashCode
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // hashé

    private String role = "USER";

    // getters + setters
}

