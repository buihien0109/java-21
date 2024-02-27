package vn.techmaster.demothymeleafspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.demothymeleafspringsecurity.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}