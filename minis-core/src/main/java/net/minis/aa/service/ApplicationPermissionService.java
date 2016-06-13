package net.minis.aa.service;

import java.util.List;

import net.minis.aa.domain.Application;
import net.minis.aa.domain.ApplicationPermission;
import net.minis.aa.domain.Role;

public interface ApplicationPermissionService {

    void savePermissions(Role role, List<Application> application);

    void savePermissions(Application application, List<Role> roles);

    ApplicationPermission savePermission(ApplicationPermission permission);

    void deletePermissionsByRoleId(String roleId);

    void deletePermissionsByApplicationId(String applicationId);

    List<ApplicationPermission> findPermissionsByApplicationId(String applicationId);

}
