package com.teddybear.reswiki.member.dto;

import com.teddybear.reswiki.member.entity.Member;

public class MemberResponse {
    public record GetMemberDto(
        String memberId,
        String memberNickname
    ) {
        public GetMemberDto(Member member) {
            this(
                    member.getMemberId(),
                    member.getMemberNickname()
            );
        }
    }

    public record JoinMemberDto(String id){
        public JoinMemberDto(Member member) { this(member.getMemberId());}
    }

    public record TokenDto(String access, String refresh){}
}
