package org.example.movie.app.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.movie.app.entity.Blog;
import org.example.movie.app.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final HttpSession session;

    // Lấy tất cả bài viết sắp xếp theo createdAt giảm dần
    public List<Blog> getAllBlogs() {
        return null;
    }

    // Lấy tất cả của user đang đăng nhập, sắp xếp theo createdAt giảm dần
    // Lấy user đang đăng nhập lấy trong session với key là "currentUser"
    // Lấy bài viết theo userId
    public List<Blog> getAllBlogOfCurrentUser() {
        return null;
    }

    // Lấy bài viết theo id
    public Blog getBlogById(Integer id) {
        return null;
    }
}
