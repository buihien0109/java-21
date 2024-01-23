package org.example.movie.app.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.movie.app.entity.User;
import org.example.movie.app.exception.BadRequestException;
import org.example.movie.app.exception.ResourceNotFoundException;
import org.example.movie.app.model.request.LoginRequest;
import org.example.movie.app.model.request.RegisterRequest;
import org.example.movie.app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final HttpSession session;

    public void login(LoginRequest request) {
        // Tìm kiếm user theo email
        // Nếu không tìm thấy thì throw exception
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Email hoặc mật khẩu không đúng"));

        // Nếu tìm thấy thì kiểm tra password
        // Nếu password không đúng thì throw exception
        if (!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Email hoặc mật khẩu không đúng");
        }

        // Lưu thông tin vào trong session
        session.setAttribute("currentUser", user);
    }

    // Về nhà làm
    public void register(RegisterRequest request) {
        // Kiểm tra xem email đã tồn tại chưa
        // Nếu tồn tại rồi thì throw exception
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email đã tồn tại");
        }

        // Kiểm tra xem password có trùng với confirm password không
        // Nếu không trùng thì throw exception
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Mật khẩu không trùng khớp");
        }

        // Mã hóa password
        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());

        // Tạo user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .build();

        // Lưu user vào database
        userRepository.save(user);
    }

    public void logout() {
        // Xóa thông tin user trong session
        // session.removeAttribute("currentUser");

        // set current user to null
        session.setAttribute("currentUser", null);
    }
}
