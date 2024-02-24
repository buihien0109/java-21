package vn.techmaster.demothymeleafspringsecurity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Cấu hình đường dẫn , 401, 403
        http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests.anyRequest().permitAll();
        });

        // Cấu hình logout
        http.logout(logout -> {
            logout.logoutSuccessUrl("/"); // Nếu logout thành công thì trở về trang chủ
            logout.deleteCookies("JSESSIONID"); // Xóa cookie JSESSIONID
            logout.invalidateHttpSession(true); // Hủy session
            logout.clearAuthentication(true); // Xóa thông tin xác thực
            logout.permitAll(); // Tất cả đều được truy cập
        });

        // Cấu hình xác thực
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
}
