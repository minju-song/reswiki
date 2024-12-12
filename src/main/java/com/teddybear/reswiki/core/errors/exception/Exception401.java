package com.teddybear.reswiki.core.errors.exception;

import com.teddybear.reswiki.core.api.ApiUtils;
import org.springframework.http.HttpStatus;

// 401 : 권한 없음
public class Exception401 extends RuntimeException{

    public Exception401(String message) {super(message);}

    public HttpStatus status() {return HttpStatus.UNAUTHORIZED; }

    public ApiUtils.Response<?> body() { return ApiUtils.error(getMessage(), HttpStatus.UNAUTHORIZED);}
}
