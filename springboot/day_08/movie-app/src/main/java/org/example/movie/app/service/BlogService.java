package org.example.movie.app.service;

import com.github.slugify.Slugify;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.movie.app.entity.Blog;
import org.example.movie.app.entity.User;
import org.example.movie.app.exception.ResourceNotFoundException;
import org.example.movie.app.model.request.UpsertBlogRequest;
import org.example.movie.app.repository.BlogRepository;
import org.example.movie.app.utils.FileUtils;
import org.example.movie.app.utils.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final HttpSession session;
    private final FileService fileService;
    private final Slugify slugify;

    // Lấy tất cả bài viết sắp xếp theo createdAt giảm dần
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll(Sort.by("createdAt").descending());
    }

    // Lấy tất cả của user đang đăng nhập, sắp xếp theo createdAt giảm dần
    // Lấy user đang đăng nhập lấy trong session với key là "currentUser"
    // Lấy bài viết theo userId
    public List<Blog> getAllBlogOfCurrentUser() {
        User user = (User) session.getAttribute("currentUser");
        return blogRepository.findByUser_Id(user.getId(), Sort.by("createdAt").descending());
    }

    // Lấy bài viết theo id
    public Blog getBlogById(Integer id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bài viết có id: " + id));
    }

    // Tạo bài viết
    public Blog createBlog(UpsertBlogRequest request) {
        User user = (User) session.getAttribute("currentUser");

        // Create blog
        Blog blog = Blog.builder()
                .title(request.getTitle())
                .slug(slugify.slugify(request.getTitle()))
                .content(request.getContent())
                .description(request.getDescription())
                .status(request.getStatus())
                .user(user)
                .thumbnail(StringUtils.generateLinkImage(request.getTitle()))
                .build();

        return blogRepository.save(blog);
    }

    // Cập nhật bài viết
    public Blog updateBlog(Integer id, UpsertBlogRequest request) {
        // find blog by id
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bài viết với id = " + id));

        // update blog
        blog.setTitle(request.getTitle());
        blog.setSlug(slugify.slugify(request.getTitle()));
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setStatus(request.getStatus());

        return blogRepository.save(blog);
    }

    // Xóa bài viết
    public void deleteBlog(Integer id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bài viết với id = " + id));

        // Kiểm tra xem blog có thumbnail không. Nếu có thì xóa file thumbnail
        if (blog.getThumbnail() != null) {
            FileUtils.deleteFile(blog.getThumbnail());
        }

        blogRepository.delete(blog);
    }

    // Upload thumbnail
    // C1: Upload file vào trong database
    // C2: Upload file vào trong thư mục trên server (image_uploads)
    public String uploadThumbnail(Integer id, MultipartFile file) {
        // Kỉem tra xem bài viết có tồn tại không
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bài viết có id: " + id));

        // Upload file vào trong thư mục trên server (image_uploads)
        String filePath = fileService.uploadFile(file);

        // Cập nhật đường dẫn thumbnail cho bài viết
        blog.setThumbnail(filePath);
        blogRepository.save(blog);

        return filePath;
    }
}
