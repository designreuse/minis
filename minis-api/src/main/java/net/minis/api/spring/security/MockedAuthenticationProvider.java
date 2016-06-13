package net.minis.api.spring.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

public class MockedAuthenticationProvider implements AuthenticationProvider {

    private AuthenticationUserDetailsService<Authentication> authenticationUserDetailsService;

    private String mockedPrincipal;

    public void setAuthenticationUserDetailsService(AuthenticationUserDetailsService<Authentication> ads) {
        this.authenticationUserDetailsService = ads;
    }

    public void setMockedPrincipal(String mockedUsername) {
        this.mockedPrincipal = mockedUsername;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return Authentication.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(mockedPrincipal, "");

        UserDetails userDetails = authenticationUserDetailsService.loadUserDetails(authenticationToken);

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        return result;
    }

}
