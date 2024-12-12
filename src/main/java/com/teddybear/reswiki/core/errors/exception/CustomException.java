package com.teddybear.reswiki.core.errors.exception;

import com.teddybear.reswiki.core.api.ApiUtils;

public abstract class CustomException extends RuntimeException{

    public CustomException(String message) { super(message); }

    public abstract Integer status();

    public ApiUtils.Response<?> body() {return ApiUtils.error(getMessage(), status());}
}
