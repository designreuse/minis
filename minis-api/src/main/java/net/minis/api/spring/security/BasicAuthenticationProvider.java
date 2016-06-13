package net.minis.api.spring.security;

/**
 * Authentication Provider.
 * 
 * @author yen.
 */
public interface BasicAuthenticationProvider {

    /**
     * Get username.
     * 
     * @return the username.
     */
    String getUsername();

    /**
     * Get password.
     * 
     * @return the password.
     */
    String getPassword();

}
