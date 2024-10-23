package com.teddybear.reswiki.member.service;

import com.teddybear.reswiki.member.dto.MemberDto;
import com.teddybear.reswiki.member.dto.MemberRequest;
import com.teddybear.reswiki.member.dto.MemberResponse;
import com.teddybear.reswiki.member.entity.Member;

public interface MemberService {

    // 중복
    public boolean checkMember(String memberId);

    // 회원가입
    public Member joinMember(MemberDto dto);

    public MemberResponse.JoinMemberDto join(MemberRequest.JoinMemberDto requestDto);
}
