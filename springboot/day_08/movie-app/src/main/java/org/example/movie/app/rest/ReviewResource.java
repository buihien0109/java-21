package org.example.movie.app.rest;

import lombok.RequiredArgsConstructor;
import org.example.movie.app.entity.Review;
import org.example.movie.app.model.request.UpsertReviewRequest;
import org.example.movie.app.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewResource {
    private final ReviewService reviewService;

    // Tạo review - POST.
    // Client -> Request chứa thông tin -> Server
    // Server đọc dữ liệu từ Request -> Xử lý -> Trả về kết quả cho Client
    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody UpsertReviewRequest request) {
        Review review = reviewService.createReview(request);
        return ResponseEntity.ok(review); // status code 200
    }

    // Cập nhật review - PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@RequestBody UpsertReviewRequest request, @PathVariable Integer id) {
        Review review = reviewService.updateReview(id, request);
        return ResponseEntity.ok(review); // status code 200
    }

    // Xóa review - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build(); // status code 204
    }
}
