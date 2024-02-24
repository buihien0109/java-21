package vn.techmaster.demothymeleafspringsecurity.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomFilter extends OncePerRequestFilter {
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Lấy email từ trong session với key MY_SESSION
        String email = (String) request.getSession().getAttribute("MY_SESSION");
        log.info("Email from session: " + email);

        // Kiểm tra xem email có tồn tại không?
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Lấy ra thông tin của user từ email
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // Tạo đối tượng phân quyền
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // Lưu thông tin request (IP, Session ID, ...)
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Lưu thông tin đối tượng vào SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Chuyển request và response cho filter tiếp theo
        filterChain.doFilter(request, response);
    }
}
