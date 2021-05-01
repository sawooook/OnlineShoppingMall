package com.shop.online.shopingMall.config;

import com.shop.online.shopingMall.exception.JWTAuthenticateException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION = "X-AUTH-TOKEN";
    private final JwtTokenProvider jwtTokenProvider;

    public LoginInterceptor(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String headerValue = request.getHeader(AUTHORIZATION);

        if (headerValue != null && jwtTokenProvider.isUsable(headerValue)) {
            return true;
        }

        response.setStatus(401);
        throw new JWTAuthenticateException();
    }
}
