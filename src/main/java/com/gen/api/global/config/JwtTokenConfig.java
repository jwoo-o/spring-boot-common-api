package com.gen.api.global.config;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import com.gen.api.global.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jinwoo.
 * User: jwoh
 * Date: 2020-05-19
 * Time: 오후 3:07
 */
@RequiredArgsConstructor
@Component
public class JwtTokenConfig {

    @Value("${spring.jwt.secret}")
    private String secretKey;

    @Value("${ext.token}")
    private String expirationTime;

    private String tokenPrefix = "Bearer ";

    @Value("${server.header.key}")
    private String headerKey;

    @Value("${client.header.key}")
    private String clientKey;


    private final CustomUserDetailService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    public String createToken(Map<String, Object> data) {
        Claims claims = Jwts.claims(data);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + Integer.parseInt(expirationTime) * 60 * 1000L)) // 토크 만료 시간
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setHeaderParam("typ", "jwt")
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadAdminById(this.getAdminId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // client 인증 정보 조회
    public Authentication getAgentAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadAgentByToken(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    // 토큰에서 회원 정보 추출
    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userId") + "";
    }

    // 토큰에서 회원 정보 추출
    public String getAdminId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("id") + "";
    }


    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(headerKey);
        if (token != null) {
            token = token.replace(tokenPrefix, "");
        } else {
            token = request.getHeader(clientKey);
        }
        return token;
    }

    public Map<String, Object> getToken(HttpServletRequest request) {
        String token = request.getHeader(headerKey);
        Map<String, Object> result = new HashMap<>();
        if (token != null) {
            token = token.replace(tokenPrefix, "");
            result.put("type", "admin");
            result.put("token", token);

        } else {
            token = request.getHeader(clientKey);
            result.put("type", "client");
            result.put("token", token);
        }

        return result;
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());

        } catch (ExpiredJwtException e) {
            throw e;
        } catch (Exception e) {
            return false;
        }
    }

    // redis token key
    public String getUserKey(String id) {

        return "user:" + id + ":refreshToken";
    }

    // redis token key
    public String getAdminKey(String id) {

        return "admin:" + id + ":refreshToken";
    }


    public String getAgentToken(HttpServletRequest request) {

        return resolveToken(request);

    }

    public String getAdminId(HttpServletRequest request) {

        return getAdminId(resolveToken(request));
    }


    public String createUserToken(Map<String, Object> data) {
        Claims claims = Jwts.claims(data);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + 3650 * 24 * 60 * 60 * 1000L)) // 토크 만료 시간
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setHeaderParam("typ", "jwt")
                .compact();
    }
}
