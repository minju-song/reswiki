package com.teddybear.reswiki.member.service;

import com.teddybear.reswiki.member.dto.MemberDto;
import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 중복 체크
    private void checkMember(String memberId) {
        memberRepository.findByMemberId(memberId)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 회원가입
    @Override
    public Member joinMember(MemberDto dto) {

        Member member = Member.toEntity(dto);

        checkMember(member.getMemberId());

        return memberRepository.save(member);
    }

}
