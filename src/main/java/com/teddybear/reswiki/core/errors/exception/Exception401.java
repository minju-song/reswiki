package com.teddybear.reswiki.core.errors.exception;

import org.springframework.http.HttpStatus;

// 401 : 권한 없음
public class Exception401 extends RuntimeException{

    public Exception401(String message) {super(message);}

    public HttpStatus status() {return HttpStatus.UNAUTHORIZED; }

}
