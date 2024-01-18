package org.example.movie.app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id tự động tăng
    Integer id;

    @Column(columnDefinition = "TEXT")
    String content;

    Integer rating;

    @Transient // Không tạo cột trong database
    String ratingText;

    Date createdAt;
    Date updatedAt;

    public String getRatingText() {
        if (rating == null) {
            return "Chưa có đánh giá";
        }

        // switch rating from 1 to 10
        return switch (rating) {
            case 1 -> "Tệ";
            case 2 -> "Kém";
            case 3 -> "Trung bình";
            case 4 -> "Tạm được";
            case 5 -> "Hay";
            case 6 -> "Rất hay";
            case 7 -> "Tuyệt vời";
            case 8 -> "Tuyệt hảo";
            case 9 -> "Xuất sắc";
            case 10 -> "Quá tuyệt vời";
            default -> "Chưa có đánh giá";
        };
    }

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist // Trước khi lưu dữ liệu vào database
    public void prePersist() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate // Trước khi cập nhật dữ liệu vào database
    public void preUpdate() {
        updatedAt = new Date();
    }
}
