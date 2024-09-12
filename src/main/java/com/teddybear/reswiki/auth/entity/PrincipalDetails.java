package com.teddybear.reswiki.auth.entity;

import com.teddybear.reswiki.member.entity.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// security가 /login 주소 요청이 오면 낚아채서 로그인 진행
// 로그인 진행이 완료되면 시큐리티 session을 만들어줌 (Security ContextHolder)
// Object -> Authentication 객체
@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private Member member;

    private Map<String, Object> attributes;

    // 일반 로그인할 때 사용
    public PrincipalDetails(Member member) {
        this.member = member;
    }

    // OAuth 로그인할 때 사용
    public PrincipalDetails(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    // 해당 유저의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getMemberRole().toString();
            }
        });
        return collect;
    }


    @Override
    public String getPassword() {
        return member.getMemberPassword();
    }

    @Override
    public String getUsername() {
        return member.getMemberNickname();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 휴먼 회원, 아닌 회원 구분
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "";
    }
}
