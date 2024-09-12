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
    private void checkMember(Member member) {
        memberRepository.findByMemberId(member.getMemberId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 회원가입
    @Override
    public Member joinMember(MemberDto dto) {

        Member member = Member.toEntity(dto);

        checkMember(member);

        return memberRepository.save(member);
    }

    @Override
    public Member login(MemberDto dto) {
        memberRepository.findByMemberId(dto.getMemberId())
                .map(m -> {
                    if (dto.getMemberPassword().equals(m.getMemberPassword())) {
                        return m;
                    } else {
                        throw new IllegalStateException("비밀번호가 틀렸습니다.");
                    }
                })
                .orElseThrow(() -> new IllegalStateException("없는 회원입니다."));
        return null;
    }
}
