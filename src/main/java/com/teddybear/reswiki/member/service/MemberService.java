package com.teddybear.reswiki.member.service;

import com.teddybear.reswiki.member.dto.MemberRequest;
import com.teddybear.reswiki.member.dto.MemberResponse;

public interface MemberService {

    // 아이디 중복
    public boolean checkMember(String memberId);

    // 회원가입
    public MemberResponse.MemberIdDto join(MemberRequest.JoinMemberDto requestDto);

    // 로그인 및 토큰 생성
    public MemberResponse.TokenDto issueJwtByLogin(MemberRequest.LoginMemberDto requestDto);

    // 로그아웃
    public void logout(String id);
}
