package org.example.movie.app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.movie.app.model.enums.MovieType;

import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id tự động tăng
    Integer id;

    String title; // Nhà bà nữ
    String slug; // nha-ba-nu

    @Column(columnDefinition = "TEXT")
    String description;

    String poster;

    @Enumerated(EnumType.STRING)
    MovieType type;

    Integer releaseYear;
    Boolean status;
    Integer rating;
    Integer view;

    Date createdAt;
    Date updatedAt;
    Date publishedAt;
}
