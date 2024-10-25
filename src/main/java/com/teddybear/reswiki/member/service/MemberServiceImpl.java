package com.teddybear.reswiki.member.service;

import com.teddybear.reswiki.core.errors.exception.Exception400;
import com.teddybear.reswiki.core.security.JwtProvider;
import com.teddybear.reswiki.member.dto.MemberRequest;
import com.teddybear.reswiki.member.dto.MemberResponse;
import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements UserDetailsService,MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final RedisTemplate<String, String> redisTemplate;


    // 회원 중복 체크
    @Override
    public boolean checkMember(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }

    // 회원가입
    @Transactional
    @Override
    public MemberResponse.JoinMemberDto join(MemberRequest.JoinMemberDto requestDto) {

        String encodedPassword = passwordEncoder.encode(requestDto.memberPassword());

        Member member = requestDto.createMember(encodedPassword);
        Member savedMember = memberRepository.save(member);
        return new MemberResponse.JoinMemberDto(savedMember);
    }

    // 로그인
    @Transactional
    @Override
    public MemberResponse.TokenDto issueJwtByLogin(MemberRequest.LoginMemberDto requestDto) {
        Member member = memberRepository.findByMemberId(requestDto.memberId()).orElseThrow(() ->
                new Exception400("아이디 혹은 비밀번호가 틀렸습니다."));

        if (!passwordEncoder.matches(requestDto.memberPassword(), member.getMemberPassword())) {
            throw new Exception400("아이디 혹은 비밀번호가 틀렸습니다.");
        }

        if(!member.getMemberRole().canBeLoggedIn()) {
            throw new Exception400("이메일 인증이 필요합니다.");
        }

        return issueToken(member);
    }

    // 회원 아이디 받아오기
    @Override
    public String getMemberId(String id) {
        Member member = memberRepository.findByMemberId(id).orElseThrow(() -> new Exception400("해당 회원 조회 불가"));
        return member.getMemberId();
    }

    // 회원 정보
    @Override
    public MemberResponse.GetMemberDto getMember(String id) {
        Member member = memberRepository.findByMemberId(id).orElseThrow(() -> new Exception400("해당 회원이 없습니다."));
        return new MemberResponse.GetMemberDto(member);
    }

    // 로그아웃
    @Override
    public void logout(String id) {
        redisTemplate.delete(id.toString());
    }

    // 토큰생성
    private MemberResponse.TokenDto issueToken(Member member) {
        String access = JwtProvider.createAccess(member);
        String refresh = JwtProvider.createRefresh(member);

        redisTemplate.opsForValue().set(
                member.getMemberId().toString(),
                refresh,
                JwtProvider.REFRESH_EXP_SEC,
                TimeUnit.SECONDS
        );

        return new MemberResponse.TokenDto(access, refresh);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
