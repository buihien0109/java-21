package org.example.movie.app.service;

import lombok.RequiredArgsConstructor;
import org.example.movie.app.entity.Actor;
import org.example.movie.app.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }
}
