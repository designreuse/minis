package net.minis.aa.service.impl;

import java.util.List;

import net.minis.aa.domain.Controlitem;
import net.minis.aa.domain.ControlitemPermission;
import net.minis.aa.domain.Role;
import net.minis.aa.repository.ControlitemPermissionDao;
import net.minis.aa.service.ControlitemPermissionService;
import net.minis.aa.service.ControlitemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControlitemPermissionServiceImpl implements ControlitemPermissionService {

    @Autowired
    private ControlitemService controlitemService;

    @Autowired
    private ControlitemPermissionDao controlitemPermissionDao;

    @Override
    public void savePermissions(Role role, List<Controlitem> controlitems) {

        // save the user and role relation.
        String roleId = role.getId();
        this.deletePermissionsByRoleId(roleId);

        for (Controlitem controlitem : controlitems) {
            ControlitemPermission permission = new ControlitemPermission();
            permission.setRole(role);
            permission.setControlitem(controlitem);
            this.savePermission(permission);
        }

    }

    @Override
    public void savePermissions(Controlitem controlitem, List<Role> roles) {

        // save the controlitem and role relation.
        String controlitemId = controlitem.getId();
        this.deletePermissionsByControlitemId(controlitemId);

        for (Role role : roles) {
            ControlitemPermission permission = new ControlitemPermission();
            permission.setRole(role);
            permission.setControlitem(controlitem);
            this.savePermission(permission);
        }

    }

    @Override
    public ControlitemPermission savePermission(ControlitemPermission permission) {
        return controlitemPermissionDao.save(permission);
    }

    @Override
    public void deletePermissionsByRoleId(String roleId) {
        controlitemPermissionDao.deleteByRoleId(roleId);
    }

    private void deletePermissionsByControlitemId(String controlitemId) {
        controlitemPermissionDao.deleteByControlitemId(controlitemId);
    }

    @Override
    public List<ControlitemPermission> findPermissionsByControlitemId(String controlitemId) {
        return controlitemPermissionDao.findByControlitemId(controlitemId);
    }

}
