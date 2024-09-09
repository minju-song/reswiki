package com.teddybear.reswiki.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    // 회원 아이디
    private String memberId;

    // 회원 비밀번호
    private String memberPassword;

    // 회원 닉네임
    private String memberNickname;

    // 회원 권한
    private String memberRole;
}
