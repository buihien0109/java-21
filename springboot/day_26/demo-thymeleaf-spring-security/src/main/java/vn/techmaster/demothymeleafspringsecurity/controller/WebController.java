package vn.techmaster.demothymeleafspringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vn.techmaster.demothymeleafspringsecurity.model.anotation.IsAdmin;
import vn.techmaster.demothymeleafspringsecurity.model.anotation.IsUser;

@Controller
public class WebController {
    // Ai cũng có thể truy cập
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    // Có role USER mới được truy cập
    // @PreAuthorize("hasRole('USER')")
    @IsUser
    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    // Có role ADMIN mới được truy cập
    // @PreAuthorize("hasRole('ADMIN')")
    @IsAdmin
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
