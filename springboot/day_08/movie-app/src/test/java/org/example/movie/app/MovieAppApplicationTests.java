package org.example.movie.app;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.example.movie.app.entity.*;
import org.example.movie.app.model.enums.MovieType;
import org.example.movie.app.model.enums.UserRole;
import org.example.movie.app.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
class MovieAppApplicationTests {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void save_all_genre() {
        Faker faker = new Faker(); // Faker data
        for (int i = 0; i < 20; i++) {
            Genre genre = Genre.builder()
                    .name(faker.name().username())
                    .build();
            genreRepository.save(genre); // Lưu vào database
        }
    }

    @Test
    void save_all_actor() {
        Faker faker = new Faker(); // Faker data
        for (int i = 0; i < 100; i++) {
            String name = faker.name().fullName();
            Actor actor = Actor.builder()
                    .name(name)
                    .birthday(faker.date().birthday())
                    .description(faker.lorem().paragraph())
                    .avatar(generateLinkImage(name))
                    .build();
            actorRepository.save(actor); // Lưu vào database
        }
    }

    @Test
    void save_all_director() {
        Faker faker = new Faker(); // Faker data
        for (int i = 0; i < 50; i++) {
            String name = faker.name().fullName();
            Director director = Director.builder()
                    .name(name)
                    .birthday(faker.date().birthday())
                    .description(faker.lorem().paragraph())
                    .avatar(generateLinkImage(name))
                    .build();
            directorRepository.save(director); // Lưu vào database
        }
    }

    @Test
    void save_all_movie() {
        List<Genre> genreList = genreRepository.findAll();
        List<Actor> actorList = actorRepository.findAll();
        List<Director> directorList = directorRepository.findAll();

        Faker faker = new Faker(); // Faker data
        Slugify slugify = Slugify.builder().build(); // Generate slug

        for (int i = 0; i < 100; i++) {
            // Random 1 -> 4 genre
            List<Genre> rdGenreList = new ArrayList<>();
            for (int j = 0; j < faker.number().numberBetween(1, 5); j++) {
                Genre rdGenre = genreList.get(faker.number().numberBetween(0, genreList.size()));
                if (!rdGenreList.contains(rdGenre)) {
                    rdGenreList.add(rdGenre);
                }
            }

            // Random 5 -> 10 actor
            List<Actor> rdActorList = new ArrayList<>();
            for (int j = 0; j < faker.number().numberBetween(5, 11); j++) {
                Actor rdActor = actorList.get(faker.number().numberBetween(0, actorList.size()));
                if (!rdActorList.contains(rdActor)) {
                    rdActorList.add(rdActor);
                }
            }

            // Random 1 -> 3 director
            List<Director> rdDirectorList = new ArrayList<>();
            for (int j = 0; j < faker.number().numberBetween(1, 4); j++) {
                Director rdDirector = directorList.get(faker.number().numberBetween(0, directorList.size()));
                if (!rdDirectorList.contains(rdDirector)) {
                    rdDirectorList.add(rdDirector);
                }
            }

            String title = faker.book().title();
            boolean status = faker.bool().bool();
            Date createdAt = faker.date().birthday();
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
                    .genres(rdGenreList)
                    .actors(rdActorList)
                    .directors(rdDirectorList)
                    .build();
            movieRepository.save(movie); // Lưu vào database
        }
    }

    @Test
    void save_all_user() {
        Faker faker = new Faker(); // Faker data
        for (int i = 0; i < 20; i++) {
            String name = faker.name().fullName();
            User user = User.builder()
                    .name(name)
                    .email(faker.internet().emailAddress())
                    .password(bCryptPasswordEncoder.encode("123"))
                    .avatar(generateLinkImage(name))
                    .role(i == 0 || i == 1 ? UserRole.ADMIN : UserRole.USER)
                    .build();

            userRepository.save(user); // Lưu vào database
        }
    }

    @Test
    void save_all_blog() {
        List<User> userList = userRepository.findByRole(UserRole.ADMIN);

        Faker faker = new Faker(); // Faker data
        Random random = new Random();
        Slugify slugify = Slugify.builder().build(); // Generate slug

        for (int i = 0; i < 20; i++) {
            // Random 1 user
            User rdUser = userList.get(random.nextInt(userList.size()));

            boolean status = faker.bool().bool();
            Date createdAt = new Date();
            Date publishedAt = null;

            if (status) {
                publishedAt = createdAt;
            }

            // Tạo blog
            String title = faker.book().title();
            Blog blog = Blog.builder()
                    .title(title)
                    .slug(slugify.slugify(title))
                    .description(faker.lorem().paragraph())
                    .content(faker.lorem().paragraph())
                    .thumbnail(generateLinkImage(title))
                    .status(status)
                    .createdAt(createdAt)
                    .updatedAt(createdAt)
                    .publishedAt(publishedAt)
                    .user(rdUser)
                    .build();
            blogRepository.save(blog); // Lưu vào database
        }
    }

    @Test
    void save_all_review() {
        List<User> userList = userRepository.findAll();
        List<Movie> movieList = movieRepository.findAll();

        Faker faker = new Faker(); // Faker data
        Random random = new Random();

        for (Movie movie : movieList) {
            // Mỗi movie có 5 -> 10 review
            for (int i = 0; i < random.nextInt(5) + 5; i++) {
                // Random 1 user
                User rdUser = userList.get(random.nextInt(userList.size()));

                // Tạo review
                Review review = Review.builder()
                        .content(faker.lorem().paragraph())
                        .rating(random.nextInt(10) + 1)
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .movie(movie)
                        .user(rdUser)
                        .build();

                // Lưu vào database
                reviewRepository.save(review);
            }
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
