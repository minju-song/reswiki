package com.teddybear.reswiki.core.errors.exception;

import com.teddybear.reswiki.core.utils.ApiUtils;
import org.springframework.http.HttpStatus;

public class Exception401 extends RuntimeException{

    public Exception401(String message) {super(message);}

    public HttpStatus status() {return HttpStatus.UNAUTHORIZED; }

    public ApiUtils.Response<?> body() { return ApiUtils.error(getMessage(), HttpStatus.UNAUTHORIZED);}
}
