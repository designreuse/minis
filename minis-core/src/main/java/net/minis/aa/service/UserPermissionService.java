package net.minis.aa.service;

import java.util.List;

import net.minis.aa.domain.DomainObject;
import net.minis.aa.domain.Role;

public interface UserPermissionService {

    List<Role> getRolePermissions(String username, DomainObject domain);

    boolean hasRolePermission(String username, DomainObject domain, String roleId);

    boolean hasApplicationPermission(String username, DomainObject domain, String actionId);

    boolean hasControlitemPermission(String username, DomainObject domain, String controlId);

}
