package org.example.movie.app.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.movie.app.entity.Blog;
import org.example.movie.app.entity.User;
import org.example.movie.app.exception.ResourceNotFoundException;
import org.example.movie.app.repository.BlogRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final HttpSession session;

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
}
