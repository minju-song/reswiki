package com.teddybear.reswiki.core.utils;

import org.springframework.http.HttpStatus;

public class ApiUtils {

    // 성공적인 응답 생성
    public static <T> Response<T> success(T response) {
        return new Response<>(HttpStatus.OK.value(), response, null);
    }

    // 성공적인 응답 생성
    // 응답 객체 없음
    public static <T> Response<T> success(){ return new Response<>(HttpStatus.OK.value(), null, null);}

    // 에러 응답 생성
    // 에러 응답 객체 존재
    public static <T> Response<T> error(String errorMessage, T response, HttpStatus status) {
        return new Response<>(status.value(), response, errorMessage);
    }

    // 에러 응답
    // 응답 객체 없음
    public static <T> Response<T> error(String errorMessage, HttpStatus status) {
        return new Response<>(status.value(), null, errorMessage);
    }

    public static <T> Response<T> error(String errorMessage, Integer status) {
        return new Response<>(status, null, errorMessage);
    }

    // API 응답
    // 상태, 응답, 에러메시지
    public record Response<T>(
            int status,
            T response,
            String errorMessage
    ){}
}
