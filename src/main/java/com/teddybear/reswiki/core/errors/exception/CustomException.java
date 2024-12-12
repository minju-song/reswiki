package com.teddybear.reswiki.core.errors.exception;

public abstract class CustomException extends RuntimeException{

    public CustomException(String message) { super(message); }

    public abstract Integer status();

}
