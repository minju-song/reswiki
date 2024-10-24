package com.teddybear.reswiki.core.errors.exception;

import com.teddybear.reswiki.core.utils.ApiUtils;
import com.teddybear.reswiki.member.dto.MemberResponse;
import org.springframework.http.HttpStatus;

// 400 : 잘못된 요청 구문
public class Exception400 extends RuntimeException{

    private MemberResponse.JoinMemberDto joinMemberDto;

    public Exception400(String message) {super(message);}

    public Exception400(String message, MemberResponse.JoinMemberDto joinMemberDto) {
        super(message);
        this.joinMemberDto = joinMemberDto;
    }

    public HttpStatus status() { return HttpStatus.BAD_REQUEST; }

    public ApiUtils.Response<?> body() { return ApiUtils.error(getMessage(), joinMemberDto, HttpStatus.BAD_REQUEST); }
}
