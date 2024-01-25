package org.example.movie.app.rest;

import lombok.RequiredArgsConstructor;
import org.example.movie.app.entity.Blog;
import org.example.movie.app.model.request.UpsertBlogRequest;
import org.example.movie.app.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/blogs")
@RequiredArgsConstructor
public class BlogResource {
    private final BlogService blogService;

    // Tạo review - POST.
    // Client -> Request chứa thông tin -> Server
    // Server đọc dữ liệu từ Request -> Xử lý -> Trả về kết quả cho Client
    @PostMapping
    public ResponseEntity<?> createBlog(@RequestBody UpsertBlogRequest request) {
        Blog blog = blogService.createBlog(request);
        return ResponseEntity.ok(blog); // status code 200
    }

    // Cập nhật review - PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@RequestBody UpsertBlogRequest request, @PathVariable Integer id) {
        Blog blog = blogService.updateBlog(id, request);
        return ResponseEntity.ok(blog); // status code 200
    }

    // Xóa review - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build(); // status code 204
    }

    // Upload thumbnail - POST
    @PostMapping("/{id}/upload-thumbnail")
    public ResponseEntity<?> uploadThumbnail(@RequestParam("file") MultipartFile file, @PathVariable Integer id) {
        String filePath = blogService.uploadThumbnail(id, file);
        return ResponseEntity.ok(filePath); // status code 200
    }
}
