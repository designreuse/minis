package net.minis.aa.service;

import java.util.List;

import net.minis.aa.domain.Controlitem;
import net.minis.aa.domain.ControlitemPermission;
import net.minis.aa.domain.Role;

public interface ControlitemPermissionService {

    void savePermissions(Role role, List<Controlitem> controlitems);

    void savePermissions(Controlitem controlitem, List<Role> roles);

    ControlitemPermission savePermission(ControlitemPermission permission);

    void deletePermissionsByRoleId(String roleId);

    List<ControlitemPermission> findPermissionsByControlitemId(String controlitemId);

}
