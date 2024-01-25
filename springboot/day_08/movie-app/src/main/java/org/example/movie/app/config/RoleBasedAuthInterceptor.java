package org.example.movie.app.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.movie.app.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Component
public class RoleBasedAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Lấy thông tin người dùng từ trong session với key currentUser
        User user = (User) request.getSession().getAttribute("currentUser");

        // Nếu currentUser không tồn tại hoặc = null thì báo lỗi 401 (unauthorized)
        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return false;
        }

        // Lấy role của user
        String role = user.getRole().getValue();
        if (Objects.equals(role, "ROLE_ADMIN")) {
            return true;
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
            return false;
        }

//        // Lấy path của request
//        String requestURI = request.getRequestURI();
//
//        if(requestURI.startsWith("/admin") || requestURI.startsWith("/api/admin")) {
//            // Nếu role của user không phải là admin thì báo lỗi 403 (forbidden)
//            if(!role.equals("ROLE_ADMIN")) {
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                return false;
//            }
//        }
//
//        return true;
    }
}
