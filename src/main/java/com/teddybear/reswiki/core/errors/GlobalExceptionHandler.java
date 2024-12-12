package com.teddybear.reswiki.core.errors;

import com.teddybear.reswiki.core.api.response.ApiResponse;
import com.teddybear.reswiki.core.api.response.ResponseService;
import com.teddybear.reswiki.core.errors.exception.Exception400;
import com.teddybear.reswiki.core.errors.exception.MemberIdAlreadyExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final ResponseService responseService;

    public GlobalExceptionHandler(ResponseService responseService) {
        this.responseService = responseService;
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        return responseService.getBadRequestResult(null);
    }

    // 아이디 중복 체크 예외
    @ExceptionHandler(MemberIdAlreadyExistException.class)
    public ResponseEntity<ApiResponse<?>> handleMemberIdAlreadyExistException(MemberIdAlreadyExistException e) {
        ApiResponse<?> errorResponse = responseService.getIdAlreadyExistResult(e.getMessage());

        return ResponseEntity.status(e.status()).body(errorResponse); // 사용자 정의 상태 코드 사용
    }

    // 아이디 유효성
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage()); // 필드명과 오류 메시지 저장
        }
        ApiResponse<?> errorResponse = responseService.getValidationJoin("유효성 검사 오류", errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<ApiResponse<?>> handle400Exception(Exception400 e) {
        ApiResponse<?> errorResponse = responseService.getBadRequestResult(e.getMessage());

        return ResponseEntity.status(e.status()).body(errorResponse);
    }
}
