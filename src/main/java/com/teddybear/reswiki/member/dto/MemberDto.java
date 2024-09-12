package com.teddybear.reswiki.member.dto;

import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.member.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    // 회원 아이디
    private String memberId;

    // 회원 비밀번호
    private String memberPassword;

    // 회원 닉네임
    private String memberNickname;

    // 회원 권한
    private Role memberRole;

    public static MemberDto toDto(Member m) {
        return MemberDto.builder()
                .memberId(m.getMemberId())
                .memberPassword(m.getMemberPassword())
                .memberNickname(m.getMemberNickname())
                .memberRole(m.getMemberRole())
                .build();
    }
}
