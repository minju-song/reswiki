package com.teddybear.reswiki.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDto {

    // auth 아이디
    private int authId;

    // 회원 아이디
    private String memberId;

    // 회원 토큰
    private String token;

    // 회원 리프레쉬 토큰
    private String refreshToken;
}
