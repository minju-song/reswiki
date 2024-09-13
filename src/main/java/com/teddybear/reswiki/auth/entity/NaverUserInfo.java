package com.teddybear.reswiki.auth.entity;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes; //oauth2User.getAttributes()

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvideId() {
        Map<String, Object> response = (Map)attributes.get("response");
        return "naver_" + (String) response.get("id");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getNickname() {
        Map<String, Object> response = (Map)attributes.get("response");
        return (String) response.get("nickname");
    }
}
