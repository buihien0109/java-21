package org.example.movie.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticationInterceptor authenticationInterceptor;
    private final RoleBasedAuthInterceptor roleBasedAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/api/reviews/**"); // Chỉ áp dụng cho các request tới các đường dẫn bên dưới

        registry.addInterceptor(roleBasedAuthInterceptor)
                .addPathPatterns("/admin", "/admin/**", "/api/admin/**") // Chỉ áp dụng cho các request tới các đường dẫn bên dưới
                .excludePathPatterns("/admin-assets/**", "/web/js/**", "/web/css/**", "/web/image/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image_uploads/**")
                .addResourceLocations("file:image_uploads/");
    }
}
