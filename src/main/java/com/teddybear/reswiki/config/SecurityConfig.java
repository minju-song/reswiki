package com.teddybear.reswiki.config;

import com.teddybear.reswiki.auth.service.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                .csrf().disable()

                .authorizeHttpRequests()
                .requestMatchers("/write", "/user/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")

                .and()
                .oauth2Login()
                .loginPage("/loginForm")

                // 구글 로그인 후 후처리
                // 1. 코드받기(인증) 2. 엑세스토큰(권한) 3. 사용자 정보 가져옴
                // 4. 회원가입 자동으로 시키거나 우리 측이 추가정보 받아서 회원가입 시킴
                // 구글 로그인 되면 엑세스토큰 + 사용자 프로필 받음
                .userInfoEndpoint()
                .userService(principalOauth2UserService);

                ;

        return httpSecurity.build();
    }
}
