package com.teddybear.reswiki.member.service;

import com.teddybear.reswiki.auth.dto.AuthResponse;
import com.teddybear.reswiki.member.dto.MemberRequest;
import com.teddybear.reswiki.member.dto.MemberResponse;
import com.teddybear.reswiki.member.entity.Member;

public interface MemberService {

    // 아이디 중복
    public boolean checkMember(String memberId);

    // 회원가입
    public MemberResponse.MemberIdDto join(MemberRequest.JoinMemberDto requestDto);

    // 로그인 및 토큰 생성
    public AuthResponse.TokenDto issueJwtByLogin(MemberRequest.LoginMemberDto requestDto);

    // 로그아웃
    public void logout(String id);

    // 회원아이디 받아오기
    public MemberResponse.MemberIdDto findMemberId(String memberId);

    // 토큰 생성
    public AuthResponse.TokenDto issueToken(Member member);
}
