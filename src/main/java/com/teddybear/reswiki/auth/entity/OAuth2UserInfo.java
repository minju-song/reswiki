package com.teddybear.reswiki.auth.entity;

public interface OAuth2UserInfo {

    String getProvideId();
    String getProvider();
    String getNickname();
}
