package com.teddybear.reswiki.auth.web;

import com.teddybear.reswiki.auth.entity.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/test/login")
    public @ResponseBody String testLogin(Authentication authentication,
                                          @AuthenticationPrincipal PrincipalDetails userDetails) {
        System.out.println("testLogin=====================");

        // 1. 데이터 변환해서 MemberObject 가질 수 있음
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("authentication : "+principalDetails.getMember());

        // 2. @AuthenticationPrincipal 통해서 Member 가질 수 있음
        System.out.println("userDetails : "+userDetails.getMember());
        return "세션 정보 확인";
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOauthLogin(Authentication authentication,
                                               @AuthenticationPrincipal OAuth2User oauth) {
        System.out.println("testLogin=====================");

        // 1. 데이터 변환해서 MemberObject 가질 수 있음
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication : "+oAuth2User.getAttributes());

        // 2. @AuthenticationPrincipal 통해서 Member 가질 수 있음
        System.out.println("oauth2User : "+oauth.getAttributes());
        return "OAuth 세션 정보 확인";
    }
}
