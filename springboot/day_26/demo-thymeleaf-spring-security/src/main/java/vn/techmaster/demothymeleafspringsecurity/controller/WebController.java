package vn.techmaster.demothymeleafspringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    // Ai cũng có thể truy cập
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    // Có role USER mới được truy cập
    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    // Có role ADMIN mới được truy cập
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }
}
