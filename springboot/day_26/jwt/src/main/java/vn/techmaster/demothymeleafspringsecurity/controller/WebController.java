package vn.techmaster.demothymeleafspringsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.demothymeleafspringsecurity.entity.anotation.IsAdmin;
import vn.techmaster.demothymeleafspringsecurity.entity.anotation.IsUser;

@RestController
@RequiredArgsConstructor
public class WebController {
    @GetMapping("/")
    public ResponseEntity<?> getHome() {
        return ResponseEntity.ok("Home page");
    }

    @IsUser
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok("Profile page");
    }

    @IsAdmin
    @GetMapping("/admin")
    public ResponseEntity<?> getAdmin() {
        return ResponseEntity.ok("Admin page");
    }
}
