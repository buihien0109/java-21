package org.example.movie.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.movie.app.entity.Movie;
import org.example.movie.app.entity.Review;
import org.example.movie.app.entity.User;
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
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public List<Review> getReviewsOfMovie(Integer movieId) {
        return reviewRepository.findByMovie_IdOrderByCreatedAtDesc(movieId);
    }

    public Review createReview(UpsertReviewRequest request) {
        // TODO: Giả định current user là user có id = 1. Sau này current user sẽ là user đang login
        Integer currentUserId = 1;
        User currentUser = userRepository.findById(currentUserId) // Kiểm tra xem user có tồn tại không
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        Movie movie = movieRepository.findById(request.getMovieId()) // Kiểm tra xem movie có tồn tại không
                .orElseThrow(() -> new RuntimeException("Phim không tồn tại"));

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
        // TODO: Giả định current user là user có id = 1. Sau này current user sẽ là user đang login
        Integer currentUserId = 1;
        User currentUser = userRepository.findById(currentUserId) // Kiểm tra xem user có tồn tại không
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        Movie movie = movieRepository.findById(request.getMovieId()) // Kiểm tra xem movie có tồn tại không
                .orElseThrow(() -> new RuntimeException("Phiim không tồn tại"));

        Review review = reviewRepository.findById(id) // Kiểm tra xem review có tồn tại không
                .orElseThrow(() -> new RuntimeException("Review không tồn tại"));

        // Kiểm tra xem user có phải là người tạo review không
        if (!review.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Bạn không có quyền cập nhật review này");
        }

        // Kiểm tra xem review có thuộc movie không
        if (!review.getMovie().getId().equals(movie.getId())) {
            throw new RuntimeException("Review không thuộc phim này");
        }

        // Cập nhật review
        review.setContent(request.getContent());
        review.setRating(request.getRating());

        // Lưu review vào database
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer id) {
        // TODO: Giả định current user là user có id = 1. Sau này current user sẽ là user đang login
        Integer currentUserId = 1;
        User currentUser = userRepository.findById(currentUserId) // Kiểm tra xem user có tồn tại không
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        Review review = reviewRepository.findById(id) // Kiểm tra xem review có tồn tại không
                .orElseThrow(() -> new RuntimeException("Review không tồn tại"));

        // Kiểm tra xem user có phải là người tạo review không
        if (!review.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Bạn không có quyền cập nhật review này");
        }

        // Xóa review
        reviewRepository.delete(review);
    }
}
