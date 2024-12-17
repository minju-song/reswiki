package com.teddybear.reswiki.member.dto;

import com.teddybear.reswiki.member.entity.Member;

public class MemberResponse {

    // 멤버 아이디
    public record MemberIdDto(
            String id
    ){
        public static MemberIdDto from(Member member) {
            return new MemberIdDto(
                    member.getMemberId()
            );
        }
    }

}
