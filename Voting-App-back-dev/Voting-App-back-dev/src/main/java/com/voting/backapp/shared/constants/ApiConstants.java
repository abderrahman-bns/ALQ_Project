package com.voting.backapp.shared.constants;

public class ApiConstants {

    private ApiConstants() {
    }

    public static final String API_V1 = "/api/v1";

    public static final String USERS = "/users";

    public static final String AUTH = "/auth";

    public static final String[] AUTH_WHITELIST = {
            "/api/v1/users", "/api/v1/auth/**",
    };

    public static final String POSTS = "/posts";
}
