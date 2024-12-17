package com.teddybear.reswiki.member.service;

import com.teddybear.reswiki.auth.dto.AuthResponse;
import com.teddybear.reswiki.core.errors.exception.Exception400;
import com.teddybear.reswiki.core.errors.exception.MemberIdAlreadyExistException;
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
        if (memberRepository.existsByMemberId(memberId)) {
            // 회원 ID가 이미 존재하는 경우 예외 던지기
            throw new MemberIdAlreadyExistException("이미 존재하는 아이디입니다 : " + memberId);
        }
        return true;
    }

    // 회원가입
    @Transactional
    @Override
    public MemberResponse.MemberIdDto join(MemberRequest.JoinMemberDto requestDto) {

        String encodedPassword = passwordEncoder.encode(requestDto.memberPassword());

        Member member = requestDto.toMember(encodedPassword);
        Member savedMember = memberRepository.save(member);
        return MemberResponse.MemberIdDto.from(savedMember);
    }

    // 로그인
    @Transactional
    @Override
    public AuthResponse.TokenDto issueJwtByLogin(MemberRequest.LoginMemberDto requestDto) {
        Member member = memberRepository.findByMemberId(requestDto.memberId()).orElseThrow(() ->
                new Exception400("이메일을 다시 한번 확인해주세요."));

        validateMember(member, requestDto.memberPassword());

        return issueToken(member);
    }

    // 비밀번호 검사
    private void validateMember(Member member, String password) {
        if (!passwordEncoder.matches(password, member.getMemberPassword())) {
            throw new Exception400("비밀번호가 틀렸습니다.");
        }

        if (!member.getMemberRole().canBeLoggedIn()) {
            throw new Exception400("이메일 인증이 필요합니다.");
        }
    }

    // 로그아웃
    @Override
    public void logout(String id) {
        redisTemplate.delete(id.toString());
    }

    @Override
    public MemberResponse.MemberIdDto findMemberId(String memberId) {
        // 회원 정보를 DB에서 가져오는 서비스 호출
        MemberResponse.MemberIdDto memberIdDto = memberRepository.findByMemberId(memberId)
                .map(member -> new MemberResponse.MemberIdDto(member.getMemberId()))
                .orElseThrow(() -> new Exception400("없는 회원입니다."));

        return  memberIdDto;
    }


    // 토큰생성
    @Override
    public AuthResponse.TokenDto issueToken(Member member) {
        System.out.println("member -> "+member);
        String access = JwtProvider.createAccess(member);
        String refresh = JwtProvider.createRefresh(member);

        redisTemplate.opsForValue().set(
                member.getMemberId().toString(),
                refresh,
                JwtProvider.REFRESH_EXP_SEC,
                TimeUnit.SECONDS
        );

        return new AuthResponse.TokenDto(access, refresh);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
