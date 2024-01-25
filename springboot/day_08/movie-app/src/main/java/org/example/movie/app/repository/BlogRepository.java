package org.example.movie.app.repository;

import org.example.movie.app.entity.Blog;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByUser_Id(Integer id, Sort sort);
}