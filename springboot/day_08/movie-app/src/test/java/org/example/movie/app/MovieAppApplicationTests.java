package org.example.movie.app;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.example.movie.app.entity.Movie;
import org.example.movie.app.model.enums.MovieType;
import org.example.movie.app.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MovieAppApplicationTests {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    void save_all_movie() {
        Faker faker = new Faker(); // Faker data
        Slugify slugify = Slugify.builder().build(); // Generate slug

        for (int i = 0; i < 100; i++) {
            String title = faker.book().title();
            boolean status = faker.bool().bool();
            Date createdAt = faker.date().birthday();
            ;
            Date publishedAt = null;

            if (status) {
                publishedAt = createdAt;
            }

            Movie movie = Movie.builder()
                    .title(title)
                    .slug(slugify.slugify(title))
                    .description(faker.lorem().paragraph())
                    .poster(faker.company().logo())
                    .type(MovieType.values()[faker.number().numberBetween(0, 2)])
                    .releaseYear(faker.number().numberBetween(2018, 2023))
                    .status(status)
                    .rating(faker.number().numberBetween(1, 10))
                    .view(faker.number().numberBetween(100, 1000))
                    .createdAt(createdAt)
                    .updatedAt(createdAt)
                    .publishedAt(publishedAt)
                    .build();
            movieRepository.save(movie); // Lưu vào database
        }
    }

    @Test
    void test_methods() {
        // Select
//        List<Movie> movies = movieRepository.findAll();
//        System.out.println(movies.size());
//
//        List<Movie> movies1 = movieRepository.findAllById(List.of(1, 2, 3, 1000));
//        System.out.println(movies1.size());

        Movie movie = movieRepository.findById(1).orElse(null);
        System.out.println(movie);

        // Update
        movie.setTitle("Nhà bà nữ");
        movieRepository.save(movie); // save() có thể dùng để update hoặc insert

        // Delete
        movieRepository.delete(movie); // Xóa theo object
        movieRepository.deleteById(2); // Xóa theo id
    }
}
