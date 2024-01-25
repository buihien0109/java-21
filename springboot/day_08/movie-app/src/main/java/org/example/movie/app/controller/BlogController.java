package org.example.movie.app.controller;

import lombok.RequiredArgsConstructor;
import org.example.movie.app.entity.Blog;
import org.example.movie.app.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/blogs")
public class BlogController {
    private final BlogService blogService;

    // Danh sách tất cả bài viết
    @GetMapping
    public String getHomePage(Model model) {
        // Lấy tất cả bài viết sắp xếp theo createdAt giảm dần
        List<Blog> blogList = blogService.getAllBlogs();
        model.addAttribute("blogList", blogList);
        return "admin/blog/index";
    }

    // Danh sách bài viết của tôi
    @GetMapping("/own-blogs")
    public String getOwnPage(Model model) {
        // Lấy tất cả của user đang đăng nhập, sắp xếp theo createdAt giảm dần
        // Lấy user đang đăng nhập lấy trong session với key là "currentUser"
        // Lấy bài viết theo userId
        List<Blog> blogList = blogService.getAllBlogOfCurrentUser();
        model.addAttribute("blogList", blogList);
        return "admin/blog/own-blog";
    }

    // Tạo bài viết
    @GetMapping("/create")
    public String getCreatePage(Model model) {
        return "admin/blog/create";
    }

    // Chi tiết bài viết
    @GetMapping("/{id}/detail")
    public String getDetailPage(@PathVariable Integer id, Model model) {
        // Lấy bài viết theo id
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "admin/blog/detail";
    }
}
