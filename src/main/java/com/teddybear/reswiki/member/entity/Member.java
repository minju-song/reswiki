package com.teddybear.reswiki.member.entity;

import com.teddybear.reswiki.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Enumerated(EnumType.STRING)
    @Column(name = "member_role")
    private Role memberRole;

    public static Member toEntity(MemberDto dto) {
        return Member.builder()
                .memberId(dto.getMemberId())
                .memberPassword(dto.getMemberPassword())
                .memberNickname(dto.getMemberNickname())
                .memberRole(dto.getMemberRole())
                .build();
    }
}
