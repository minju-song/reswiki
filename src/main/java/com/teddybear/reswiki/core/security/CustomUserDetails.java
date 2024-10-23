package com.teddybear.reswiki.core.security;

import com.teddybear.reswiki.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public record CustomUserDetails(Member member) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(member.getMemberRole().toString()));
        return auth;
    }

    public String getMemberId() { return member.getMemberId(); }

    @Override
    public String getPassword() { return member.getMemberPassword(); }

    @Override
    public String getUsername() { return member.getMemberNickname(); }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
