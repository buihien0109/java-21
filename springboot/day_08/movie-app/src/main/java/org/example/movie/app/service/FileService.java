package org.example.movie.app.service;

import org.example.movie.app.exception.BadRequestException;
import org.example.movie.app.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {
    public static final String UPLOAD_FOLDER = "image_uploads";

    public FileService() {
        FileUtils.createDirectory(UPLOAD_FOLDER);
    }


    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BadRequestException("Không thể tải lên tệp rỗng");
        }

        // Validate file size, file type, file extension, ...

        try {
            // Tạo tên tệp duy nhất với UUID
            String fileName = UUID.randomUUID().toString();

            // Tạo đường dẫn lưu tệp (/image_uploads/fileName)
            Path path = Paths.get(UPLOAD_FOLDER + File.separator + fileName);

            // Lưu tệp
            Files.copy(file.getInputStream(), path);

            return File.separator + UPLOAD_FOLDER + File.separator + fileName; // /image_uploads/fileName
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể tải lên tệp");
        }
    }
}
