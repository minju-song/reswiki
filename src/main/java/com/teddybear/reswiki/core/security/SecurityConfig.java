package com.teddybear.reswiki.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teddybear.reswiki.auth.service.PrincipalOauth2UserService;
import com.teddybear.reswiki.core.config.Configs;
import com.teddybear.reswiki.core.errors.exception.Exception401;
import com.teddybear.reswiki.core.errors.exception.Exception403;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration //스프링 설정 클래스
@Slf4j // 로깅
public class SecurityConfig {

    // PrincipalOauth2UserService를 주입
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    // 비밀번호 인코더 Bean 설정
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

    // SecurityFilterChain 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 1. csrf 해제
        http.csrf(AbstractHttpConfigurer::disable); // 프론트 분리일 경우 CSRF 해제

        // 2. iframe 거부 설정
        http.headers(header ->
                header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)); // 동일 출처에서만 허용

        // 3. CORS 재설정
        http.cors(cors ->
                cors.configurationSource(configurationSource()));

        // 4. Stateless 세션 관리 설정
        http.sessionManagement(management ->
                management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션 사용 안함

        // 5. form 로그인 비활성화
        http.formLogin(form ->
                form.loginPage("http://localhost:3000/login") // 로그인 페이지 설정
                        .loginProcessingUrl("http://localhost:9892/login") // 로그인 처리 URL 설정
                        .defaultSuccessUrl("http://localhost:3000/")); // 로그인 성공 후 리다이렉트

        // 6.OAuth2 설정
        http.oauth2Login(oauth2 ->
                oauth2.loginPage("http://localhost:3000/login") // 로그인 페이지 설정
                        .userInfoEndpoint(userInfo ->
                                userInfo.userService(principalOauth2UserService) // 사용자 정보 서비스 설정
                        )
//                        .defaultSuccessUrl("http://localhost:3000/") // 리다이렉트
        );

        // 7. 요청 권한 설정
        http.authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/user/**").authenticated() // /member/* 경로는 모두 인증 필요
                        .anyRequest().permitAll() // 나머지 요청은 허용
        );

        // 8. HTTP Basic 인증 비활성화
        http.httpBasic(AbstractHttpConfigurer::disable); // 기본 인증 비활성화

        // 9. 커스텀 필터 적용
        http.apply(new CustomSecurityFilterManager()); // 커스텀 시큐리티 필터 매니저 적용

        // 10. 인증 실패 처리
        http.exceptionHandling(handling ->
                handling.authenticationEntryPoint(((request, response, authException) -> {
                    var e = new Exception401("인증되지 않았습니다."); // 인증 실패 메시지
                    response.setStatus(e.status().value()); // HTTP 상태 코드 설정
                    response.setContentType("application/json; charset=utf-8");
                    ObjectMapper om = new ObjectMapper();
                    String responseBody = om.writeValueAsString(e.body()); // json 형식으로 응답 본문 생성
                    response.getWriter().println(responseBody); // 응답 본문 출력
                })));

        // 11. 권한 실패
        http.exceptionHandling(handling ->
                handling.accessDeniedHandler(((request, response, accessDeniedException) -> {
                    var e = new Exception403("권한이 없습니다."); // 권한 실패 메시지
                    response.setStatus(e.status().value()); // HTTP 상태 코드 설정
                    response.setContentType("application/json; charset=utf-8");
                    ObjectMapper om = new ObjectMapper();
                    String responseBody = om.writeValueAsString(e.body()); // json 형식으로 응답 본문 생성
                    response.getWriter().println(responseBody); // 응답 본문 출력
                })));

        return http.build();
    }

    // CORS 구성 소스 메소드
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration(); // cors 설정 객체 생성
        configuration.addAllowedHeader("*"); // 모든 헤더 허용
        configuration.addAllowedMethod("*"); // 모든 http 메소드 허용
        configuration.setAllowedOrigins(Configs.CORS); // 허용된 출처 설정
        configuration.setAllowCredentials(true); // 쿠키 허용
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 cors 설정
        return source;
    }
}