package com.asgeek.books.web.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.List;

public class TokenAuthentication  extends AbstractAuthenticationToken {
    private final String token;
    private final String subject;
    private final List<String> roles;

    /**
     * Constructor
     * @param token: token jwt
     * @param subject: username or email
     * @param roles: roles
     */
    public TokenAuthentication(String token, String subject, List<String> roles) {
        super(null);
        this.token = token;
        this.subject = subject;
        this.roles = roles;
    }

    @Override
    public boolean isAuthenticated() {
        return !token.isEmpty() && !subject.isEmpty();
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return subject;
    }
}
