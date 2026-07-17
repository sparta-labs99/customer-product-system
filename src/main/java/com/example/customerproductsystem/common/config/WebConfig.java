package com.example.customerproductsystem.common.config;
import com.example.customerproductsystem.auth.interceptor.LoginCheckInterceptor;
import com.example.customerproductsystem.auth.interceptor.SuperAdminCheckInterceptor;
import com.example.customerproductsystem.auth.interceptor.CsAdminCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptor;
    private final SuperAdminCheckInterceptor superAdminCheckInterceptor;
    private final CsAdminCheckInterceptor csAdminCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 로그인 인증 인터셉터
        registry.addInterceptor(loginCheckInterceptor)
                .order(1)
                .addPathPatterns(
                        "/admins/**", 
                        "/view/**", 
                        "/customers",
                        "/customers/**", 
                        "/orders/**", 
                        "/products/**",
                        "/dashboard/**",
                        "/reviews/**"
                )
                .excludePathPatterns(
                        "/login",
                        "/signup",
                        "/css/**",
                        "/js/**",
                        "/assets/**",
                        "/error"
                );

        // 슈퍼 관리자 권한 인터셉터
        registry.addInterceptor(superAdminCheckInterceptor)
                .order(2)
                .addPathPatterns("/admins/**", "/customers", "/customers/**")
                .excludePathPatterns(
                        "/signup",
                        "/login",
                        "/logout",
                        "/admins/me",
                        "/admins/me/**"
                );

        // CS 관리자 권한 인터셉터
        registry.addInterceptor(csAdminCheckInterceptor)
                .order(3)
                .addPathPatterns("/orders/**");
    }
}