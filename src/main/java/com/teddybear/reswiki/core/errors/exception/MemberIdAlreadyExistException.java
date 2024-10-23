package com.teddybear.reswiki.core.errors.exception;

public class MemberIdAlreadyExistException extends CustomException{

    public MemberIdAlreadyExistException(String message) {super(message);}
    @Override
    public Integer status() { return 461; }
}
