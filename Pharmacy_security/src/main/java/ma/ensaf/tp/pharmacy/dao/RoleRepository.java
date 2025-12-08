package ma.ensaf.tp.pharmacy.dao;

import ma.ensaf.tp.pharmacy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByNom(String nom);
}