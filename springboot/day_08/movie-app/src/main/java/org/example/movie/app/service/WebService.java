package org.example.movie.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.movie.app.entity.Movie;
import org.example.movie.app.model.enums.MovieType;
import org.example.movie.app.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebService {
    private final MovieRepository movieRepository;

    public List<Movie> getMoviesByType(MovieType movieType, Boolean status, Sort sort) {
        return movieRepository.findByTypeAndStatus(movieType, status, sort);
    }

    public Page<Movie> getMoviesByType(MovieType movieType, Boolean status, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("publishedAt").descending()); // page trong jpa bắt đầu từ 0
        return movieRepository.findByTypeAndStatus(movieType, status, pageRequest);
    }

    public Movie getMovie(Integer id, String slug, Boolean status) {
        return movieRepository.findByIdAndSlugAndStatus(id, slug, status).orElse(null);
    }

    public Page<Movie> getHotMovies(Boolean status, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("view").descending()); // page trong jpa bắt đầu từ 0
        return movieRepository.findByStatus(status, pageRequest);
    }

    public List<Movie> getRelatedMovies(Integer id, MovieType type, Boolean status, Integer size) {
        return movieRepository
                .findByTypeAndStatusAndRatingGreaterThanEqualAndIdNotOrderByRatingDescViewDescPublishedAtDesc(type, status, 5, id)
                .stream()
                .limit(size)
                .toList();
    }
}
