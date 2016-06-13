package net.minis.aa.service.impl;

import java.util.List;

import net.minis.aa.domain.DomainObject;
import net.minis.aa.domain.Role;
import net.minis.aa.domain.RoleGroup;
import net.minis.aa.repository.ApplicationPermissionDao;
import net.minis.aa.repository.ControlitemPermissionDao;
import net.minis.aa.repository.RoleDao;
import net.minis.aa.repository.RoleGroupDao;
import net.minis.aa.service.UserPermissionService;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

/**
 * 
 * @author yen.
 */
@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleGroupDao userRoleDao;

    @Autowired
    private ApplicationPermissionDao actionPermissionDao;

    @Autowired
    private ControlitemPermissionDao controlPermissionDao;

    @Override
    public List<Role> getRolePermissions(String username, DomainObject domain) {

        List<Role> roles = Lists.newArrayList();

        // get user role relation to roles.
        List<RoleGroup> userRoles = userRoleDao.findByUsername(username);

        for (RoleGroup userRole : userRoles) {
            Role role = userRole.getRole();
            roles.add(role);
        }

        // get user domain role relation to role ids.
        // TODO 之後再接 Webhooks

        return roles;
    }

    private List<String> getRolePermissionIds(String username, DomainObject domain) {

        List<String> roleIds = Lists.newArrayList();

        // get user role relation to roles.
        List<RoleGroup> userRoles = userRoleDao.findByUsername(username);
        
        for (RoleGroup userRole : userRoles) {
            String roleId = userRole.getRole().getId();
            roleIds.add(roleId);
        }

        // get user domain role relation to role ids.
        // TODO 之後再接 Webhooks

        return roleIds;
    }

    @Override
    public boolean hasRolePermission(String username, DomainObject domain, String roleId) {
        List<String> roleIds = getRolePermissionIds(username, domain);
        return roleIds.contains(roleId);
    }

    @Override
    public boolean hasApplicationPermission(String username, DomainObject domain, String actionId) {

        List<String> roleIds = getRolePermissionIds(username, domain);
        Long isContains = actionPermissionDao.count(roleIds, actionId);

        if (isContains != null && isContains > 0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean hasControlitemPermission(String username, DomainObject domain, String controlId) {

        List<String> roleIds = getRolePermissionIds(username, domain);

        if (CollectionUtils.isEmpty(roleIds)) {
            return false;
        }

        Long isContains = controlPermissionDao.count(roleIds, controlId);

        if (isContains != null && isContains > 0) {
            return true;
        }

        return false;
    }

}
