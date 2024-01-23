package org.example.movie.app.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.movie.app.entity.Movie;
import org.example.movie.app.entity.Review;
import org.example.movie.app.entity.User;
import org.example.movie.app.exception.BadRequestException;
import org.example.movie.app.exception.ResourceNotFoundException;
import org.example.movie.app.model.request.UpsertReviewRequest;
import org.example.movie.app.repository.MovieRepository;
import org.example.movie.app.repository.ReviewRepository;
import org.example.movie.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final HttpSession session;

    public List<Review> getReviewsOfMovie(Integer movieId) {
        return reviewRepository.findByMovie_IdOrderByCreatedAtDesc(movieId);
    }

    public Review createReview(UpsertReviewRequest request) {
        // Lấy thông tin user từ trong session với key currentUser
        User currentUser = (User) session.getAttribute("currentUser");

        Movie movie = movieRepository.findById(request.getMovieId()) // Kiểm tra xem movie có tồn tại không
                .orElseThrow(() -> new ResourceNotFoundException("Phim không tồn tại"));

        // Tạo review
        Review review = Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .movie(movie)
                .user(currentUser)
                .build();

        // Lưu review vào database
        return reviewRepository.save(review);
    }

    public Review updateReview(Integer id, UpsertReviewRequest request) {
        // Lấy thông tin user từ trong session với key currentUser
        User currentUser = (User) session.getAttribute("currentUser");

        Movie movie = movieRepository.findById(request.getMovieId()) // Kiểm tra xem movie có tồn tại không
                .orElseThrow(() -> new ResourceNotFoundException("Phim không tồn tại"));

        Review review = reviewRepository.findById(id) // Kiểm tra xem review có tồn tại không
                .orElseThrow(() -> new ResourceNotFoundException("Review không tồn tại"));

        // Kiểm tra xem user có phải là người tạo review không
        if (!review.getUser().getId().equals(currentUser.getId())) {
            throw new BadRequestException("Bạn không có quyền cập nhật review này");
        }

        // Kiểm tra xem review có thuộc movie không
        if (!review.getMovie().getId().equals(movie.getId())) {
            throw new BadRequestException("Review không thuộc phim này");
        }

        // Cập nhật review
        review.setContent(request.getContent());
        review.setRating(request.getRating());

        // Lưu review vào database
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer id) {
        // Lấy thông tin user từ trong session với key currentUser
        User currentUser = (User) session.getAttribute("currentUser");

        Review review = reviewRepository.findById(id) // Kiểm tra xem review có tồn tại không
                .orElseThrow(() -> new ResourceNotFoundException("Review không tồn tại"));

        // Kiểm tra xem user có phải là người tạo review không
        if (!review.getUser().getId().equals(currentUser.getId())) {
            throw new BadRequestException("Bạn không có quyền xóa review này");
        }

        // Xóa review
        reviewRepository.delete(review);
    }
}
