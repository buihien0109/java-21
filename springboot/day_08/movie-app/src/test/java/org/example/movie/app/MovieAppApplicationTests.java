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
                    .poster(generateLinkImage(title))
                    .type(MovieType.values()[faker.number().numberBetween(0, 3)])
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

    // get first character from string, and to uppercase
    private static String getCharacter(String str) {
        return str.substring(0, 1).toUpperCase();
    }

    // generate link author avatar follow struct : https://placehold.co/200x200?text=[...]
    public static String generateLinkImage(String name) {
        return "https://placehold.co/200x200?text=" + getCharacter(name);
    }
}
