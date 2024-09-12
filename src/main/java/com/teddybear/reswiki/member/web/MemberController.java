package com.teddybear.reswiki.member.web;

import com.teddybear.reswiki.auth.entity.PrincipalDetails;
import com.teddybear.reswiki.member.dto.MemberDto;
import com.teddybear.reswiki.member.entity.Member;
import com.teddybear.reswiki.member.entity.Role;
import com.teddybear.reswiki.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

        // 비밀번호 암호화
        String ecnPassword = bCryptPasswordEncoder.encode(member.getMemberPassword());
        member.setMemberPassword(ecnPassword);

        // 회원 권한 설정
        member.setMemberRole(Role.ROLE_USER);

        Member result = memberService.joinMember(member);
        return ResponseEntity.ok("/loginForm");
    }

    @GetMapping("/member")
    public @ResponseBody String member(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("잘 받았니? ->> "+principalDetails.getMember());
        return "member";
    }


}
