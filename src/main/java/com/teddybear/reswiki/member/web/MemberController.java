package com.teddybear.reswiki.member.web;

import com.teddybear.reswiki.auth.entity.PrincipalDetails;
import com.teddybear.reswiki.core.utils.ApiUtils;
import com.teddybear.reswiki.member.dto.MemberRequest;
import com.teddybear.reswiki.member.dto.MemberResponse;
import com.teddybear.reswiki.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/checkMemberId")
    public ResponseEntity<?> checkMemberId(@RequestParam("checkId") String checkId) {
        boolean checkMemberId = memberService.checkMember(checkId);
        ApiUtils.Response<?> result;
        if(!checkMemberId) result = ApiUtils.success();
        else result = ApiUtils.error("이미 존재하는 아이디입니다.", 461);
        return ResponseEntity.ok().body(result);
    }


    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid MemberRequest.JoinMemberDto member) {
        System.out.println(member);
        MemberResponse.JoinMemberDto result = memberService.join(member);

        return ResponseEntity.ok().body(ApiUtils.success(result));
    }

    @GetMapping("/member")
    public @ResponseBody String member(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("잘 받았니? ->> "+principalDetails.getMember());
        return "member";
    }


}
