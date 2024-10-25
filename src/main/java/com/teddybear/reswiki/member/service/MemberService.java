package com.teddybear.reswiki.member.service;

import com.teddybear.reswiki.member.dto.MemberRequest;
import com.teddybear.reswiki.member.dto.MemberResponse;

public interface MemberService {

    // 아이디 중복
    public boolean checkMember(String memberId);

    // 회원가입
    public MemberResponse.JoinMemberDto join(MemberRequest.JoinMemberDto requestDto);

    // 로그인 및 토큰 생성
    public MemberResponse.TokenDto issueJwtByLogin(MemberRequest.LoginMemberDto requestDto);

    // 아이디 받아오기
    public String getMemberId(String id);

    // 마이페이지
    public MemberResponse.GetMemberDto getMember(String id);

    // 로그아웃
    public void logout(String id);
}
