package com.teddybear.reswiki.member.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Member {

    // 회원 아이디 - 이메일
    @Id
    @Column(name = "member_id", unique = true)
    private String memberId;

    // 회원 비밀번호
    @Column(name = "member_password")
    private String memberPassword;

    // 회원 닉네임
    @Column(name = "member_nickname")
    private String memberNickname;

    // 회원 권한
    @Column(name = "member_role")
    private String memberRole;

}
