package org.example.movie.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.movie.app.entity.Episode;
import org.example.movie.app.exception.ResourceNotFoundException;
import org.example.movie.app.repository.EpisodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final VideoService videoService;

    // Lấy danh sách tập phim của movie sắp xếp theo displayOrder tăng dần
    public List<Episode> getEpisodeListOfMovie(Integer movieId) {
        return episodeRepository.findByMovie_IdOrderByDisplayOrderAsc(movieId);
    }

    public List<Episode> getEpisodeListOfMovie(Integer movieId, Boolean status) {
        return episodeRepository.findByMovie_IdAndStatusOrderByDisplayOrderAsc(movieId, status);
    }

    public void uploadVideo(Integer id, MultipartFile file) {
        log.info("Uploading video for episode with id = {}", id);
        log.info("File name: {}", file.getOriginalFilename());

        // Kiểm tra tập phim có tồn tại không
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tập phim có id = " + id));

        // Upload video
        String videoUrl = videoService.uploadVideo(file);

        episode.setVideoUrl(videoUrl);
        episodeRepository.save(episode);
    }

    public Episode getEpisode(Integer movieId, String tap, boolean episodeStatus) {
        if(tap.equals("full")) {
            return episodeRepository.findByMovie_IdAndDisplayOrderAndStatus(movieId, 1, episodeStatus).orElse(null);
        } else {
            return episodeRepository.findByMovie_IdAndDisplayOrderAndStatus(movieId, Integer.parseInt(tap), episodeStatus).orElse(null);
        }
    }
}
