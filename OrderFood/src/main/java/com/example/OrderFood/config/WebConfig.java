package com.example.OrderFood.config;

import com.example.OrderFood.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by 永恒星辰
 * Date 2024/3/1 2:23
 * Description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**") // 注意顺序，先拦截后排除,一星任意1个字符，两星任意所有字符
                .excludePathPatterns(
                        "/api/login/**",
                        "/images/**"
                );
    }
}