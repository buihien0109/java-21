package org.example.movie.app.service;

import lombok.extern.slf4j.Slf4j;
import org.example.movie.app.exception.BadRequestException;
import org.example.movie.app.utils.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class VideoService {
    public static final String UPLOAD_FOLDER = "video_uploads";
    public static final long CHUNK_SIZE = 100000L;

    public VideoService() {
        FileUtils.createDirectory(UPLOAD_FOLDER);
    }

    public ResourceRegion getVideoResourceRegion(String fileName, long start, long end) throws IOException {
        UrlResource videoResource = new UrlResource("file:" + UPLOAD_FOLDER + File.separator + fileName);

        if (!videoResource.exists() || !videoResource.isReadable()) {
            throw new IOException("Video not found");
        }

        Resource video = videoResource;
        long contentLength = video.contentLength();
        end = Math.min(end, contentLength - 1);

        long rangeLength = Math.min(CHUNK_SIZE, end - start + 1);
        return new ResourceRegion(video, start, rangeLength);
    }

    // Sử dụng cơ chế bất đồng bộ để tải lên video (Async)
    public String uploadVideo(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BadRequestException("Không thể tải lên tệp rỗng");
        }

        // TODO: Về bổ sung logic
        // Validate file size, file type, file extension, ...
        // Nén video
        // Trích xuất thông tin video: duration, format, resolution, ...

        try {
            // Tạo tên tệp duy nhất với UUID
            String fileName = UUID.randomUUID().toString();

            // Tạo đường dẫn lưu tệp (/image_uploads/fileName)
            Path path = Paths.get(UPLOAD_FOLDER + File.separator + fileName);

            // Lưu tệp
            Files.copy(file.getInputStream(), path);

            return File.separator + "api" + File.separator + "videos" + File.separator + fileName; // path = /api/videos/21341313
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể tải lên tệp");
        }
    }
}
