package com.teddybear.reswiki.auth.service;

import com.teddybear.reswiki.auth.entity.PrincipalDetails;
import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// securityconfig에서 설정한 loginProcessingUrl실행 시 실행됨
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    // 시큐리티 세션 내부에 Authentication (얘 내부엔 UserDetails)
    // 함수 종료 시 @AuthenticationPrincipal 어노테이션이 만들어짐
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(username).get();
        System.out.println("아이디 : "+username);
        if(member != null) {
            return new PrincipalDetails(member);
        }


        return null;
    }
}
