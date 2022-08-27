package com.example.hongber.common.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;
    @Value("${jasypt.encryptor.password}")
    private String secret;

    // 토큰에서 사용자 검색
    public String getMemberIdxFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // 토큰에서 만료 날짜 검색
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // 회원 정보 추출
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // 토큰 만료일자 확인
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // 토큰 생성
    public String generateToken(Long memberIdx) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, memberIdx);
    }

    // 토큰 발급
    private String doGenerateToken(Map<String, Object> claims, Long memberIdx) {
        return Jwts.builder().setClaims(claims).setSubject(memberIdx + "").setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // 토큰 유효성 + 만료일자 확인
    public Boolean validateToken(String token, Long memberIdx) {
        final Long idx = Long.valueOf(getMemberIdxFromToken(token));
        return (idx.equals(memberIdx) && ! isTokenExpired(token));
    }
}