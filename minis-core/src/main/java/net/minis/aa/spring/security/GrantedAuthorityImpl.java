package net.minis.aa.spring.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@EqualsAndHashCode(of = "authority", callSuper = false)
public class GrantedAuthorityImpl implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String authority;

}
