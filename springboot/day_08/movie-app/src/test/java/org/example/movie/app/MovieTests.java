package org.example.movie.app;

import org.example.movie.app.entity.Movie;
import org.example.movie.app.model.enums.MovieType;
import org.example.movie.app.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class MovieTests {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    void test_findByTypeAndStatusNQ() {
        PageRequest pageRequest = PageRequest.of(2, 5);
        Page<Movie> pageData = movieRepository.findByTypeAndStatusNQ(MovieType.PHIM_CHIEU_RAP, true, pageRequest);

        System.out.println("Total elements: " + pageData.getTotalElements());
        System.out.println("Total elements in page: " + pageData.getContent().size());
        pageData.getContent().forEach(System.out::println);

    }
}
