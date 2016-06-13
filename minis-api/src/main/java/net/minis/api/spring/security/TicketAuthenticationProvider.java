package net.minis.api.spring.security;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class TicketAuthenticationProvider implements AuthenticationProvider {

    private TicketAuthenticationService ticketAuthenticationService = null;

    public void setTicketAuthenticationService(TicketAuthenticationService tas) {
        this.ticketAuthenticationService = tas;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TicketAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("Ticket authentication Principal[{}], Credentials[{}].", 
                authentication.getPrincipal(), authentication.getCredentials());

        UserDetails userDetails = null;

        try {

            userDetails = ticketAuthenticationService
                    .loadUserDetails((TicketAuthenticationToken) authentication);

        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Unable to authenticate.", e);
        }

        if (userDetails == null) {
            throw new BadCredentialsException("Unable to authenticate.");
        }

        TicketAuthenticationToken result = new TicketAuthenticationToken(
                userDetails, authentication.getCredentials(), userDetails.getAuthorities());

        result.setDetails(userDetails);

        return result;
    }

}
