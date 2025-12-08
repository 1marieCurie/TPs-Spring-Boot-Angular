package ma.ensaf.tp.pharmacy.dao;

import ma.ensaf.tp.pharmacy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}