package com.teddybear.reswiki.auth.entity;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes; //oauth2User.getAttributes()

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }


    @Override
    public String getProvideId() {
        return "kakao_" + String.valueOf(attributes.get("id"));
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getNickname() {
        Map<String, Object> properties = (Map)attributes.get("properties");
        return (String) properties.get("nickname");
    }
}
