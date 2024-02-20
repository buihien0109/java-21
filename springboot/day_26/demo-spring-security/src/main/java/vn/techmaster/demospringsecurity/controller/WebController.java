package vn.techmaster.demospringsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.demospringsecurity.security.IAuthenticationFacade;

import java.security.Principal;

@RestController
public class WebController {
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    // Ai cũng có thể truy cập
    @GetMapping("/")
    public String getHome() {
        return "Home page";
    }

    // Có role USER mới được truy cập
    @GetMapping("/profile")
    public String getProfile() {
        return "Profile page";
    }

    // Có role ADMIN mới được truy cập
    @GetMapping("/admin")
    public String getAdmin() {
        return "Admin page";
    }

    // Cachs 1: Lấy thông tin người dùng từ SecurityContextHolder
    @GetMapping("/get-info")
    public ResponseEntity<?> getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
    }

    // Cách 2: Lấy thông tin người dùng từ Principal
    @GetMapping("/get-info-2")
    public ResponseEntity<?> getInfo2(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    // Cách 3: Lấy thông tin người dùng từ Authentication
    @GetMapping("/get-info-3")
    public ResponseEntity<?> getInfo3(Authentication authentication) {
        return ResponseEntity.ok(authentication);
    }

    // Cách 4: Lấy thông tin người dùng từ HttpServletRequest
    @GetMapping("/get-info-4")
    public ResponseEntity<?> getInfo4(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return ResponseEntity.ok(principal);
    }

    // Cách 5: Lấy thông tin người dùng từ IAuthenticationFacade (custom interface)
    @GetMapping("/get-info-5")
    public ResponseEntity<?> getInfo5() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return ResponseEntity.ok(authentication);
    }
}
