package com.recruitmentbe.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String MANAGER = "ROLE_MANAGER";

    public static final String CANDIDATE = "ROLE_CANDIDATE";

    public static final String EMPLOYER = "ROLE_EMPLOYER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
