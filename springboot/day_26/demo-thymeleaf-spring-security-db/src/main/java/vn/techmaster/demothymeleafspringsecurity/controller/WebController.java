package vn.techmaster.demothymeleafspringsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.techmaster.demothymeleafspringsecurity.entity.anotation.IsAdmin;
import vn.techmaster.demothymeleafspringsecurity.entity.anotation.IsUser;
import vn.techmaster.demothymeleafspringsecurity.service.AuthService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final AuthService authService;

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

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping("/xac-thuc-tai-khoan")
    public String getAccountConfirmPage(@RequestParam String token, Model model) {
        Map<String, Object> data = authService.confirmAccount(token);
        model.addAttribute("data", data);
        return "account-confirm";
    }
}
