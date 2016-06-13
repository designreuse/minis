package net.minis.aa.spring.security;

import java.io.Serializable;
import java.util.Objects;

import net.minis.aa.domain.DomainObject;
import net.minis.aa.service.UserPermissionService;
import net.minis.api.exception.NotSupportedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class PermissionEvaluatorImpl implements PermissionEvaluator {

    @Autowired
    private UserPermissionService userPermissionService;

    @Override
    public boolean hasPermission(Authentication authentication,
            Object domainObject, Object permission) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails == null) {
            return false;
        }

        DomainObject roleDomain = new DomainObject();
        if (domainObject instanceof DomainObject) {
            roleDomain = (DomainObject) domainObject;
        }

        String username = userDetails.getUsername();

        boolean hasPermission = userPermissionService.hasControlitemPermission(
                username, roleDomain, Objects.toString(permission));

        return hasPermission;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
            Serializable targetId, String targetType, Object permission) {
        throw new NotSupportedException();
    }

}
