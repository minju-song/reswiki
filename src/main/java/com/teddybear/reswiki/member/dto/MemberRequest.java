package com.teddybear.reswiki.member.dto;

import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.member.entity.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MemberRequest {
    public record LoginMemberDto(
            @NotEmpty @Size(max = 40, message = "최대 40자까지 입력 가능합니다.")
            String memberId,
            @NotEmpty @Size(max = 64, message = "최대 64자까지 입력 가능합니다.")
            String memberPassword
    ){}

    public record JoinMemberDto(
            @NotEmpty @Size(min = 4, max = 40, message = "4자 이상 40자 이하여야 합니다.")
            @Pattern(regexp = "^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\\.[A-Za-z]{2,3}$", message = "이메일만 가능합니다.")
            String memberId,
            @NotEmpty @Size(min = 8, max = 64, message = "8자 이상 64자 이하여야 합니다.")
            @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[\\d@#$%^&!])([a-zA-Z\\d@#$%^&!]{8,64})$", message = "영문, 숫자, 특수문자 중 최소 2종류를 포함해야 합니다.")
            String memberPassword,
            @Size(max = 40, message = "최대 40자까지 입니다.")
            String memberNickname
    ) {
        public Member createMember(String EncodePassword) {
            return Member.builder()
                    .memberId(memberId)
                    .memberPassword(EncodePassword)
                    .memberNickname(memberNickname)
                    .memberRole(Role.ROLE_USER)
                    .build();
        }
    }
}
