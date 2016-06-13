package net.minis.api.spring.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface TicketAuthenticationService {

    UserDetails loadUserDetails(TicketAuthenticationToken authentication);

}
