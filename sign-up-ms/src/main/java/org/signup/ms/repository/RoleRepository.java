package org.signup.ms.repository;

import org.signup.ms.entities.ERole;
import org.signup.ms.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
     Optional<Roles> findByName (ERole role);
}
