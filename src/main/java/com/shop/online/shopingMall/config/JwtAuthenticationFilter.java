package com.shop.online.shopingMall.config;

import com.shop.online.shopingMall.exception.JWTAuthenticateException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    private final JwtTokenProvider jwtTokenProvider;



    /*
    * 회원가입, 로그인 페이지의 경우 유효성검사를 하지 않도록함
    * */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = ((HttpServletRequest) request).getRequestURI();

        if (!(url.equals("/user/signUp") || url.equals("/user/login"))) {
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
            if (isValidToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new JWTAuthenticateException("JWT 토큰 유효성 에러");
            }
        }
        chain.doFilter(request, response);
    }

    private boolean isValidToken(String token) {
        return token == null && jwtTokenProvider.validToken(token);
    }
}
