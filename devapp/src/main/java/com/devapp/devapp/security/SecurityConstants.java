package com.devapp.devapp.security;

import javax.crypto.SecretKey;

public class SecurityConstants {
    public static final String SecretKey = "haykel@esprit.tn";
    public static final long EXPIRATION_TIME = 864_000_000;//10days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
