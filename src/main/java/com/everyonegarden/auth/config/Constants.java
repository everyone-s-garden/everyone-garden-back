package com.everyonegarden.auth.config;

public class Constants {


    /**
     * 권한제외 대상
     * @see SecurityConfig
     */
    public static final String[] permitAllArray = new String[] {
            "/",
            "/auth/**",
            "/v1/weather/**",
            "/v1/crop**",
            "/v1/garden/all/by-region**",
            "/v1/garden/public/by-region**",
            "/v1/garden/private/by-region**",
            "/v1/garden/all/by-coordinate**",
            "/v1/garden/public/by-coordinate**",
            "/v1/garden/private/by-coordinate**",
            "/v1/garden/recent**",
            "/v1/garden?**"
    };
}

