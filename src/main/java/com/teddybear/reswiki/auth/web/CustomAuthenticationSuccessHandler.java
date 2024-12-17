package com.teddybear.reswiki.auth.web;

import com.teddybear.reswiki.auth.dto.AuthResponse;
import com.teddybear.reswiki.auth.entity.PrincipalDetails;
import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.member.repository.MemberRepository;
import com.teddybear.reswiki.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Member member = memberRepository.findByMemberId(principalDetails.getUsername()).get();
        AuthResponse.TokenDto tokenDto = memberService.issueToken(member);

        String url = makeRedirectUrl(tokenDto.access());

        if(response.isCommitted()) {
            System.out.println("응답");
            return;
        }
        response.setHeader("Authorization", tokenDto.access());
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("JWT: " + tokenDto.access());

        response.sendRedirect(url);
    }

    private String makeRedirectUrl(String token) {
        return UriComponentsBuilder.fromUriString("http://localhost:3000/oauth2/redirect")
                .queryParam("token", token)
                .build().toUriString();
    }
}
