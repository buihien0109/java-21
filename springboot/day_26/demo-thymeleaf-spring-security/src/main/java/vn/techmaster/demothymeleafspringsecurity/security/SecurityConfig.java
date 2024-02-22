package vn.techmaster.demothymeleafspringsecurity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Cấu hình đường dẫn , 401, 403
        String[] publicPath = {"/phim-le", "/phim-bo", "/phim-chieu-rap", "/tin-tuc"};
        http.authorizeHttpRequests(authorizeRequests -> {
//            authorizeRequests.requestMatchers("/").permitAll(); // Ai cũng được truy cập
//            authorizeRequests.requestMatchers("/css/**", "/js/**", "/image/**").permitAll(); // Các đường dẫn static
//            authorizeRequests.requestMatchers(publicPath).permitAll(); // Các đường dẫn public
//            authorizeRequests.requestMatchers("/profile").hasRole("USER"); // Có role USER mới được truy cập
//            authorizeRequests.requestMatchers("/admin").hasRole("ADMIN"); // Có role ADMIN mới được truy cập
//            authorizeRequests.requestMatchers("/author").hasAnyRole("ADMIN", "AUTHOR"); // Có role ADMIN hoặc AUTHOR mới được truy cập
//            authorizeRequests.requestMatchers("/users", "/movies").hasAuthority("ROLE_ADMIN"); // Có authority ROLE_ADMIN mới được truy cập
//            authorizeRequests.requestMatchers("/khach-hang", "/don-hang").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN"); // Có authority ROLE_USER hoặc ROLE_ADMIN mới được truy cập
//            authorizeRequests.requestMatchers("GET", "/aa/**", "/bb/**").hasRole("ADMIN"); // Có role ADMIN mới được truy cập
//            authorizeRequests.anyRequest().authenticated(); // Tất cả các request khác đều cần xác thực
            authorizeRequests.anyRequest().permitAll();
        });

        // Cấu hình form login
        http.formLogin(formLogin -> {
            formLogin.loginPage("/login"); // Đường dẫn trang login
            formLogin.loginProcessingUrl("/handle-login"); // Đường dẫn xử lý login
            formLogin.usernameParameter("email"); // Tham số của input email
            formLogin.passwordParameter("pass"); // Tham số của input password
            formLogin.defaultSuccessUrl("/", true); // Nếu login thành công thì trở về trang chủ
            formLogin.permitAll(); // Tất cả đều được truy cập
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
