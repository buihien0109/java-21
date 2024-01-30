package org.example.movie.app.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.movie.app.service.VideoService;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoResource {
    private final VideoService videoService;

    // Tạo video - POST.
    @PostMapping
    public ResponseEntity<?> createVideo(@RequestParam("video") MultipartFile video) {
        String path = videoService.uploadVideo(video); // path = /api/videos/21341313
        return ResponseEntity.ok(path); // status code 200
    }

    @GetMapping(value = "/{fileName}", produces = "application/octet-stream")
    public ResponseEntity<ResourceRegion> streamVideo(@PathVariable String fileName, HttpServletRequest request) throws IOException {
        long rangeStart = 0;
        long rangeEnd = Long.MAX_VALUE;

        String rangeHeader = request.getHeader("Range");
        if (rangeHeader != null && rangeHeader.contains("bytes=")) {
            String[] ranges = rangeHeader.substring("bytes=".length()).split("-");
            rangeStart = Long.parseLong(ranges[0]);
            rangeEnd = ranges.length > 1 ? Long.parseLong(ranges[1]) : Long.MAX_VALUE;
        }

        ResourceRegion resourceRegion = videoService.getVideoResourceRegion(fileName, rangeStart, rangeEnd);

        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resourceRegion);
    }
}
