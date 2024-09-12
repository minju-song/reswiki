package com.teddybear.reswiki.auth.entity;

import com.teddybear.reswiki.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// security가 /login 주소 요청이 오면 낚아채서 로그인 진행
// 로그인 진행이 완료되면 시큐리티 session을 만들어줌 (Security ContextHolder)
// Object -> Authentication 객체
public class PrincipalDetails implements UserDetails {

    private Member member;

    public PrincipalDetails(Member member) {
        this.member = member;
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
}
