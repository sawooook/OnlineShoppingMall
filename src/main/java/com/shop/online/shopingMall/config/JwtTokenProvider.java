package com.shop.online.shopingMall.config;

import com.shop.online.shopingMall.exception.JWTAuthenticateException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final String CHARSET = "UTF-8";
    private static final String SECRET = "shoppingMall";

    public String generateToken(String userId) {
        try {
            return Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .claim("scope", "normal")
                    .claim("name", userId)
                    .setExpiration(new Date(System.currentTimeMillis() + (3 * 60 * 60 * 1000))) // 3시간
                    .signWith(
                            SignatureAlgorithm.HS256,
                            genKey())
                    .compact();
        } catch (UnsupportedEncodingException e) {
            throw new JWTAuthenticateException();
        }
    }


    public boolean isUsable(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(genKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new JWTAuthenticateException();
        }
    }

    private byte[] genKey() throws UnsupportedEncodingException {
        return SECRET.getBytes(CHARSET);
    }
}
