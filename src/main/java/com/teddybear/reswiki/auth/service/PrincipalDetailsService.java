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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(username).get();

        if(member != null) {
            return new PrincipalDetails(member);
        }


        return null;
    }
}
