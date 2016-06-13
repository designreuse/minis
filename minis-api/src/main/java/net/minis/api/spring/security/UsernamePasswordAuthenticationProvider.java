package net.minis.api.spring.security;

import static net.minis.api.spring.security.AuthenticationUtils.UNKNOWN;

import org.apache.commons.lang3.Validate;
import org.springframework.security.core.userdetails.UserDetails;

public class UsernamePasswordAuthenticationProvider implements BasicAuthenticationProvider {

    final private String ERROR = "spring security dosen't authenticated before.";

    @Override
    public String getUsername() {
        String username = AuthenticationUtils.getUsername();
        Validate.isTrue(!UNKNOWN.equals(username), ERROR);
        return username;
    }

    @Override
    public String getPassword() {
        UserDetails userDetails = AuthenticationUtils.getPrincipal();
        Validate.notNull(userDetails, ERROR);
        return userDetails.getPassword();
    }

}
