package com.teddybear.reswiki.member.web;

import com.teddybear.reswiki.auth.dto.AuthResponse;
import com.teddybear.reswiki.core.api.response.ApiResponse;
import com.teddybear.reswiki.core.api.response.ResponseService;
import com.teddybear.reswiki.core.security.CustomUserDetails;
import com.teddybear.reswiki.core.security.JwtProvider;
import com.teddybear.reswiki.member.dto.MemberRequest;
import com.teddybear.reswiki.member.dto.MemberResponse;
import com.teddybear.reswiki.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입
    @PostMapping
    public MemberResponse.MemberIdDto postMember(@RequestBody @Valid MemberRequest.JoinMemberDto member) {

        MemberResponse.MemberIdDto result = memberService.join(member);

        return result;
    }

    // 아이디 중복 확인
    @GetMapping("/check-email")
    public boolean checkMemberId(@RequestParam("checkId") String checkId) {

        return memberService.checkMember(checkId);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid MemberRequest.LoginMemberDto member) {

        // access, refresh 토큰
        AuthResponse.TokenDto result = memberService.issueJwtByLogin(member);

        // refresh토큰 저장할 쿠키 생성
        ResponseCookie responseCookie = createRefreshTokenCookie("", 0);

        ApiResponse<?> response = new ApiResponse<>(ResponseService.CommonResponse.SUCCESS.getCode(), ResponseService.CommonResponse.SUCCESS.getMsg(), null);

        return ResponseEntity.ok().header(JwtProvider.HEADER, result.access()) // 응답 헤더에 엑세스 토큰
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString()) // 응답 헤더에 리프레쉬 토큰
                .body(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal CustomUserDetails userDetails) {
        memberService.logout(userDetails.getMemberId());

        var responseCookie = createRefreshTokenCookie("", 0);

        ApiResponse<?> response = new ApiResponse<>(ResponseService.CommonResponse.SUCCESS.getCode(), ResponseService.CommonResponse.SUCCESS.getMsg(), null);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(response);
    }

    @GetMapping("/myInfo")
    public MemberResponse.MemberIdDto getMyInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {

        MemberResponse.MemberIdDto memberIdDto = memberService.findMemberId(userDetails.getMemberId());

        return memberIdDto;
    }

    // 쿠키 생성
    private static ResponseCookie createRefreshTokenCookie(String refreshToken, int maxAge) {
        return ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true) // javascript 접근 방지
                .secure(true) // https 통신 강제
                .sameSite("None")
                .maxAge(maxAge)
                .build();
    }



}
