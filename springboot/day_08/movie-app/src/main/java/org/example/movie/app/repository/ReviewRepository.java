package org.example.movie.app.repository;

import org.example.movie.app.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByMovie_IdOrderByCreatedAtDesc(Integer movieId);
}