package vn.techmaster.demothymeleafspringsecurity.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.techmaster.demothymeleafspringsecurity.model.request.LoginRequest;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final HttpSession session;

    public void login(LoginRequest request) {
        // Tạo đối tượng xác thực
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        try {
            // Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(token);

            // Lưu thông tin xác thực vào SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Lưu email vào session
            session.setAttribute("MY_SESSION", request.getEmail());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email/password");
        }
    }
}
