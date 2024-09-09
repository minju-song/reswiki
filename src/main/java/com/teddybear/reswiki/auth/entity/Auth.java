package com.teddybear.reswiki.auth.entity;

import com.teddybear.reswiki.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Auth {

    // auth 아이디 - PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private int authId;

    // 회원 아이디 - FK
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member memberId;

    // 회원 토큰
    @Column(name = "token")
    private String token;

    // 회원 리프레쉬 토큰
    @Column(name = "refresh_token")
    private String refreshToken;
}
