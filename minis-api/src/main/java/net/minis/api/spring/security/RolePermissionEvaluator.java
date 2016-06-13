package net.minis.api.spring.security;

import java.io.Serializable;

import net.minis.api.exception.NotSupportedException;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class RolePermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object domainObject, Object permission) {

//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//        if (userDetails == null) {
//            return false;
//        }
//
//        RoleDomain roleDomain = new RoleDomain();
//
//        if (domainObject instanceof RoleDomain) {
//            roleDomain = (RoleDomain) domainObject;
//        }
//
//        String username = userDetails.getUsername();
//
//        boolean hasPermission = userPermissionService.hasControlPermission(
//                username, roleDomain, Objects.toString(permission));
//
//        return hasPermission;
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new NotSupportedException();
    }

}
