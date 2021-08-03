package com.example.authentication.config;

public interface SecurityParams {

    public static final String JWT_HEADER_NAME = "Authorization";
    public static final String JWT_SECRET = "${jwt.secret}";
    public static final long JWT_TOKEN_EXPIRATION = 5 * 24 * 3600 * 1000;
    public static final String JWT_HEADER_PREFIX = "Bearer ";

}
