package com.teddybear.reswiki.core.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
    private boolean success;

    // 생성자
    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = true;
    }

    // 예외 발생 시 사용할 생성자
    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
        this.success = false;
    }
}
