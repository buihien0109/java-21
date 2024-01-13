package org.example.movie.app.repository;

import org.example.movie.app.entity.Movie;
import org.example.movie.app.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// Movie : Đối tuợng cần thao tác
// Integer : Kiểu dữ liệu của khóa chính
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    // Tìm kiếm phim theo tiêu đề
    List<Movie> findByTitle(String title);

    // JPQL
    @Query("SELECT m FROM Movie m WHERE m.title = ?1")
    List<Movie> findByTitleJPQL(String title);

    @Query("SELECT m FROM Movie m WHERE m.title = :title")
    List<Movie> findByTitleJPQL1(@Param("title") String title);

    // Native Query
    @Query(value = "SELECT * FROM movies WHERE title = ?1", nativeQuery = true)
    List<Movie> findByTitleNQ(String title);

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

    // Gợi ý
    // C1:
    List<Movie> findByTypeAndStatusOrderByPublishedAtDesc(MovieType type, boolean status);

    // C2:
    List<Movie> findByTypeAndStatus(MovieType type, boolean status, Sort sort);

    // C3: Bổ sung thêm phân trang
    Page<Movie> findByTypeAndStatus(MovieType type, boolean status, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * FROM movies WHERE type = :type AND status = :status",
            countQuery = "SELECT COUNT(*) FROM movies WHERE type = :type AND status = :status")
    Page<Movie> findByTypeAndStatusNQ(@Param("type") MovieType type, @Param("status") boolean status, Pageable pageable);

    List<Movie> findByTypeAndStatus(MovieType movieType, Boolean status, Sort sort);

    Optional<Movie> findByIdAndSlugAndStatus(Integer id, String slug, Boolean status);

    Page<Movie> findByStatus(Boolean status, Pageable pageable);

    // Thay đổi thông tin đối tượng
    // update
    @Modifying
    @Query("UPDATE Movie m SET m.title = ?1 WHERE m.id = ?2") // jpql
    void updateTitleById(String title, Integer id);

    // delete
    @Modifying
    @Query("DELETE FROM Movie m WHERE m.id = :id AND m.slug = :slug")
    void deleteByIdAndSlug(@Param("id") Integer id, @Param("slug") String slug);

    // sách phim đề xuất sẽ là những phim có cùng type với phim đó, có status = true, và có rating >= 8,
    // và sắp xếp theo rating giảm dần, view giảm dần, publishedAt giảm dần. Nhưng không chứa phim có id = 1
    // C1: Method query
    List<Movie> findByTypeAndStatusAndRatingGreaterThanEqualAndIdNotOrderByRatingDescViewDescPublishedAtDesc(
            MovieType type,
            Boolean status,
            Integer rating,
            Integer id
    );

    // C2: JPQL
    @Query("SELECT m FROM Movie m WHERE m.type = :type AND m.status = true AND m.rating >= 8 AND m.id != :id ORDER BY m.rating DESC, m.view DESC, m.publishedAt DESC")
    List<Movie> findRecommendedMovies(MovieType type, Integer id);

    // C3: Native Query
    @Query(value = "SELECT * FROM movies WHERE type = :type AND status = true AND rating >= 8 AND id != :id ORDER BY rating DESC, view DESC, published_at DESC", nativeQuery = true)
    List<Movie> findRecommendedMoviesNQ(MovieType type, Integer id);
}
