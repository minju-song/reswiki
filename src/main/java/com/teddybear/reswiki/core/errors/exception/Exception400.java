package com.teddybear.reswiki.core.errors.exception;

import com.teddybear.reswiki.core.api.ApiUtils;
import com.teddybear.reswiki.member.dto.MemberResponse;
import org.springframework.http.HttpStatus;

// 400 : 잘못된 요청 구문
public class Exception400 extends RuntimeException{

    private MemberResponse.MemberIdDto memberIdDto;

    public Exception400(String message) {super(message);}

    public Exception400(String message, MemberResponse.MemberIdDto joinMemberDto) {
        super(message);
        this.memberIdDto = joinMemberDto;
    }

    public HttpStatus status() { return HttpStatus.BAD_REQUEST; }

    public ApiUtils.Response<?> body() { return ApiUtils.error(getMessage(), memberIdDto, HttpStatus.BAD_REQUEST); }
}
