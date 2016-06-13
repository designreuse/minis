package net.minis.api.spring.security;

import java.io.Serializable;

import net.minis.api.exception.NotSupportedException;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class PermitAllPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new NotSupportedException();
    }

}
