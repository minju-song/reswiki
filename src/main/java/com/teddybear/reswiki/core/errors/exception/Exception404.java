package com.teddybear.reswiki.core.errors.exception;

import com.teddybear.reswiki.core.api.ApiUtils;
import org.springframework.http.HttpStatus;

// 404 : 지정한 리소스 찾을 수 없음
public class Exception404 extends RuntimeException{

    public Exception404(String message) { super(message); }

    public HttpStatus status() { return HttpStatus.NOT_FOUND; }

    public ApiUtils.Response<?> body() { return ApiUtils.error(getMessage(), HttpStatus.NOT_FOUND); }
}
