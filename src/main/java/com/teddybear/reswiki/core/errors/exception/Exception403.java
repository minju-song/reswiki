package com.teddybear.reswiki.core.errors.exception;

import com.teddybear.reswiki.core.api.response.ApiResponse;
import org.springframework.http.HttpStatus;

// 403 : 지정한 리소스에 대한 접근 금지
public class Exception403 extends RuntimeException{

    public Exception403(String message) {super(message); }

    public HttpStatus status() { return HttpStatus.FORBIDDEN; }

    public ApiResponse body() {
        return new ApiResponse(403, getMessage());
    }

}
