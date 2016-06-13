package net.minis.aa.spring.security;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "username", callSuper = false)
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String email;

    private String name;

    private String localName;

    private String locale;

    private boolean isEnabled;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private Set<GrantedAuthority> authorities;

}
