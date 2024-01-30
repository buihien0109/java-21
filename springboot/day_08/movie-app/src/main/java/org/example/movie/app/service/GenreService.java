package org.example.movie.app.service;

import lombok.RequiredArgsConstructor;
import org.example.movie.app.entity.Genre;
import org.example.movie.app.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
