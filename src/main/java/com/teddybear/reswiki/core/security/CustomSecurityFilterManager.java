package com.teddybear.reswiki.core.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity> {
    // Http Security 구성 메소드 오버라이드
    @Override
    public void configure(HttpSecurity builder) throws Exception {
        // 인증 처리하는 객체
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        // Jwt 기반 인증 처리
        builder.addFilter(new JwtAuthenticationFilter(authenticationManager));
        super.configure(builder);
    }
}
