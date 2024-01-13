package org.example.movie.app.repository;

import org.example.movie.app.entity.User;
import org.example.movie.app.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(UserRole userRole);
}