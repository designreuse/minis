package net.minis.api.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class AuthenticationUtils {

    /**
     * unknown.
     */
    public static final String UNKNOWN = "unknown";

    /**
     * Utility classes should not have a public or default constructor.
     */
    private AuthenticationUtils() {
    }

    /**
     * @return user id.
     */
    public static String getUsername() {

        UserDetails userDetails = getPrincipal();

        if (userDetails != null) {
            return userDetails.getUsername();
        }

        return UNKNOWN;
    }

    /**
     * @return principal object.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getPrincipal() {

        SecurityContext context = SecurityContextHolder.getContext();

        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal != null) {
                    return (T) principal;
                }
            }
        }

        return null;
    }

}
