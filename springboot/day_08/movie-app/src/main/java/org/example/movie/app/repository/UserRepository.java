package org.example.movie.app.repository;

import org.example.movie.app.entity.User;
import org.example.movie.app.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(UserRole userRole);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}