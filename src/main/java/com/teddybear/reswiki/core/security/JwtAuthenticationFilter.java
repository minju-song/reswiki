package com.teddybear.reswiki.core.security;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.member.entity.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 요청 헤더에서 JWT 가져옴
        String jwt = request.getHeader(JwtProvider.HEADER);

        // jwt없으면 다음 필터로
        if(jwt == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            // jwt 검증하고 디코딩
            DecodedJWT decodedJWT = JwtProvider.verify(jwt);

            // jwt에서 사용자 아이디랑 role 가져옴
            String id = decodedJWT.getClaim("id").asString();
            Role role = decodedJWT.getClaim("role").as(Role.class);

            // 멤버 객체 생성
            Member member = Member.builder()
                    .memberId(id)
                    .memberRole(role)
                    .build();

            // 사용자 세부 정보를 기반으로 CustomUserDetails 생성
            CustomUserDetails userDetails = new CustomUserDetails(member);

            // 인증 객체 생성
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            userDetails.getPassword(),
                            userDetails.getAuthorities()
                    );

            // SecurityContext에 인증 객체 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 디버그 로그
            log.debug("인증 객체 생성");
        } catch (TokenExpiredException e) {
            log.error("토큰 만료");
        } catch (Exception e) {
            log.error("토큰 검증 실패");
        } finally {
            // 필터 체인의 다음 필터로 요청과 응답을 전달
            chain.doFilter(request, response);
        }
    }


}
