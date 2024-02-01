package org.example.movie.app.rest;

import lombok.RequiredArgsConstructor;
import org.example.movie.app.service.EpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/episodes")
@RequiredArgsConstructor
public class EpisodeResource {
    private final EpisodeService episodeService;

    @PostMapping("/{id}/upload-video")
    public ResponseEntity<?> uploadVideo(@RequestParam("video") MultipartFile file, @PathVariable Integer id) {
        episodeService.uploadVideo(id, file);
        return ResponseEntity.ok().build(); // status code 200
    }
}
