package net.minis.api.spring.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class TicketAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1L;

    private final Object principal;

    private final Object credentials;

    public TicketAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
    }

    public TicketAuthenticationToken(Object principal, Object credentials,
            Collection<? extends GrantedAuthority> authorities) {

        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}
