package com.shop.online.shopingMall.config;

import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.exception.JWTAuthenticateException;
import com.shop.online.shopingMall.service.SecurityService;
import com.shop.online.shopingMall.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.PushBuilder;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private String secretKey = "shoppingMall";

    // 토큰 유효시간 30분으로 설정
    private long tokenValidTime = 30 * 60 * 1000L;
    private final SecurityService securityService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /*
    * claims => JWT payload에 저장되는 정보 단위
    *
    * */
    public String createToken(Long userPk) {

        Claims claims = Jwts.claims().setSubject(String.valueOf(userPk));
        Date now = new Date();

        return Jwts.builder().setClaims(claims).setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /*
    * 토큰에서 회원 정보 추출
    * */
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }


    /*
    * 토큰이 유효한 토큰인지 파악
    *
    * 만약 시간이 지나지 않은 토큰이고, 발행이 유효한 토큰일 경우 true를 반환한다
    *
    * */
    public boolean validToken(String token) {
        //토큰을 파싱
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = securityService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
