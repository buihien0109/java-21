package vn.techmaster.demothymeleafspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.demothymeleafspringsecurity.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String role);
}