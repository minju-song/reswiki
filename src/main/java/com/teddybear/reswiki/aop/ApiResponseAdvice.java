package com.teddybear.reswiki.aop;

import com.teddybear.reswiki.core.api.response.ApiResponse;
import com.teddybear.reswiki.core.api.response.ResponseService;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    private final ResponseService responseService;

    public ApiResponseAdvice(ResponseService responseService) {
        this.responseService = responseService;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ApiResponse) {
            return body; // 이미 ApiResponse인 경우 그대로 반환
        }

        if (body instanceof Resource) {
            return body;
        }


        // 요청 메서드 확인
        String method = request.getMethod().name();

        // POST 요청인 경우
        if ("POST".equalsIgnoreCase(method)) {
            return responseService.getCreatedResult(body);
        }

        // GET 요청 등 다른 경우
        return responseService.getSuccessResult(body);
    }
}
