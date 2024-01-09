package org.example.movie.app.repository;

import org.example.movie.app.entity.Movie;
import org.example.movie.app.model.enums.MovieType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Movie : Đối tuợng cần thao tác
// Integer : Kiểu dữ liệu của khóa chính
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    // Tìm kiếm phim theo tiêu đề
    List<Movie> findByTitle(String title);

    // Tìm kiếm phim theo tiêu đề chứa từ khóa
    List<Movie> findByTitleContaining(String title);

    // Kiểm tra phim có tồn tại theo tiêu đề
    boolean existsByTitle(String title);

    // Đếm số lượng phim theo tiêu đề
    long countByTitle(String title);

    // Tìm kiếm phim theo tiêu đề và trạng thái
    List<Movie> findByStatusAndType(boolean status, MovieType type);

    // Sắp xếp các bộ phim theo view giảm dần
    List<Movie> findAllByOrderByViewDesc();

    // Tìm kiếm phim theo trạng thái và sắp xếp theo tiêu chí nào đấy
    List<Movie> findByStatus(boolean status, Sort sort);
}
