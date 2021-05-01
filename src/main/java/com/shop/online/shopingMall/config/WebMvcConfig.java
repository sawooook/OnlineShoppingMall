package com.shop.online.shopingMall.config;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private HandlerInterceptor handlerInterceptor;

    public WebMvcConfig(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns = Arrays.asList("/", "/user/signUp", "/user/login");
        registry.addInterceptor(handlerInterceptor)
                .excludePathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
