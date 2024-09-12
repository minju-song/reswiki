package com.teddybear.reswiki.member.web;

import com.teddybear.reswiki.member.dto.MemberDto;
import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.member.entity.Role;
import com.teddybear.reswiki.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member/join")
    public ResponseEntity<String> join(MemberDto member) {
        System.out.println(member.getMemberNickname());

        // 비밀번호 암호화
        String ecnPassword = bCryptPasswordEncoder.encode(member.getMemberPassword());
        member.setMemberPassword(ecnPassword);

        // 회원 권한 설정
        member.setMemberRole(Role.ROLE_USER);

        Member result = memberService.joinMember(member);
        return ResponseEntity.ok("/loginForm");
    }


}
