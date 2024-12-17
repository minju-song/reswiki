package com.teddybear.reswiki.core.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.teddybear.reswiki.member.entity.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class JwtProvider {
    // 액세스 토큰 만료 시간 = 5분
    public static final int ACCESS_EXP_SEC = 60 * 50;
    // refresh 토큰 만료 시간 = 30일
    public static final int REFRESH_EXP_SEC = 60 * 60 * 24 * 30;
    // 토큰 접두사
    public static final String TOKEN_PREFIX = "Bearer ";
    // 요청 헤더 이름
    public static final String HEADER = "Authorization";
    // 비밀키
    private static String secret;

    // 비밀키 값 설정
    @Value("${reswiki.secret}")
    public void setKey(String value) {secret = value;}

    // 액세스 토큰 생성 메소드
    public static String createAccess(Member member) {
        LocalDateTime now = LocalDateTime.now();
        // 만료 시간 계산
        LocalDateTime expired = now.plusSeconds(ACCESS_EXP_SEC);
        // JWT 생성
        String jwt = JWT.create()
                .withExpiresAt(Timestamp.valueOf(expired)) // 만료 시간 설정
                .withClaim("id", member.getMemberId()) // 사용자 ID 설정
                .withClaim("role", String.valueOf(member.getMemberRole())) // 사용자 ROLE 설정
                .sign(Algorithm.HMAC512(secret)); // 서명
        return TOKEN_PREFIX + jwt; // Bearer과 함께 토큰 리턴
    }

    // refresh 토큰 생성 메소드
    public static String createRefresh(Member member) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.plusSeconds(REFRESH_EXP_SEC);
        return JWT.create()
                .withExpiresAt(Timestamp.valueOf(expired))
                .withClaim("id", member.getMemberId())
                .sign(Algorithm.HMAC512(secret));
    }

    // JWT 검증 메소드
    public static DecodedJWT verify(String jwt) {
        System.out.println(jwt);
        // 접두사 제거
        return JWT.require(Algorithm.HMAC512(secret)).build()
                .verify(jwt.replace(TOKEN_PREFIX, ""));
    }
}
