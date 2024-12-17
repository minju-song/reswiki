package com.teddybear.reswiki.auth.dto;

public class AuthResponse {

    public record TokenDto(String access, String refresh){}
}
