package net.minis.aa.service.impl;

import java.util.List;

import net.minis.aa.domain.Application;
import net.minis.aa.domain.ApplicationPermission;
import net.minis.aa.domain.Role;
import net.minis.aa.repository.ApplicationPermissionDao;
import net.minis.aa.service.ApplicationPermissionService;
import net.minis.aa.service.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationPermissionServiceImpl implements
        ApplicationPermissionService {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationPermissionDao applicationPermissionDao;

    @Override
    public void savePermissions(Role role, List<Application> applications) {

        // save the role and application relation.
        String roleId = role.getId();
        this.deletePermissionsByRoleId(roleId);

        for (Application application : applications) {
            ApplicationPermission permission = new ApplicationPermission();
            permission.setRole(role);
            permission.setApplication(application);
            this.savePermission(permission);
        }

    }

    @Override
    public void savePermissions(Application application, List<Role> roles) {

        // save the application and role relation.
        String applicationId = application.getId();
        this.deletePermissionsByApplicationId(applicationId);

        for (Role role : roles) {
            ApplicationPermission permission = new ApplicationPermission();
            permission.setRole(role);
            permission.setApplication(application);
            this.savePermission(permission);
        }

    }

    @Override
    public ApplicationPermission savePermission(ApplicationPermission permission) {
        return applicationPermissionDao.save(permission);
    }

    @Override
    public void deletePermissionsByRoleId(String roleId) {
        applicationPermissionDao.deleteByRoleId(roleId);
    }

    @Override
    public void deletePermissionsByApplicationId(String applicationId) {
        applicationPermissionDao.deleteByApplicationId(applicationId);
    }

    @Override
    public List<ApplicationPermission> findPermissionsByApplicationId(String applicationId) {
        return applicationPermissionDao.findByApplicationId(applicationId);
    }

}
