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
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id tự động tăng
    Integer id;

    String name; // Tên tập phim
    Integer displayOrder; // Số thứ tự của tập phim
    String videoUrl; // Link video của tập phim
    Integer duration; // Thời lượng của tập phim
    Boolean status; // Trạng thái của tập phim

    Date createdAt;
    Date updatedAt;
    Date publishedAt;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
