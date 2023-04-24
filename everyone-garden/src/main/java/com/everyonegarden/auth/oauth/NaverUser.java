package com.everyonegarden.auth.oauth;

import java.util.Map;

public class NaverUser implements OAuthUserInfo {


    private Map<String, Object> attribute;


    public NaverUser(Map<String, Object> attribute){
        this.attribute = attribute;
    }

    @Override
    public String getProviderId() {
        return (String)attribute.get("id");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return (String)attribute.get("email");
    }

    @Override
    public String getName() {
        return null;
    }
}
