package com.teddybear.reswiki.member.service;

import com.teddybear.reswiki.member.dto.MemberDto;
import com.teddybear.reswiki.member.entity.Member;

public interface MemberService {

    // 회원가입
    public Member joinMember(MemberDto dto);

}
