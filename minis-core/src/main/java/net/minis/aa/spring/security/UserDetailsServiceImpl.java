package net.minis.aa.spring.security;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import net.minis.aa.domain.User;
import net.minis.aa.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = null;

        try {
            user = userService.getUserByUsername(username);
        } catch (Exception e) {
            log.debug("[ACL] find user by username catch an exception.", e);
        }

        if (user == null) {
            String message = "could not found user details. username = "
                    + username;
            throw new UsernameNotFoundException(message);
        }

        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(username);
        userDetails.setPassword(user.getPassword());
        userDetails.setEmail(user.getEmail());
        userDetails.setName(user.getName());

        userDetails.setLocalName(user.getLocalName());
        userDetails.setLocale(user.getLocale());

        userDetails.setEnabled(user.isEnabled());
        userDetails.setAccountNonLocked(!user.isLocked());
        userDetails.setAccountNonExpired(!user.isExpired());
        userDetails.setCredentialsNonExpired(!user.isPasswordExpired());

        userDetails.setAuthorities(getAuthoritiesByUsername(username));
        return userDetails;
    }

    private Set<GrantedAuthority> getAuthoritiesByUsername(String username) {
        // not yet implement.
        return null;
    }

}
