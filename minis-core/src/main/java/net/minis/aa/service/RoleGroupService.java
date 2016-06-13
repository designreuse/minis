package net.minis.aa.service;

import java.util.List;

import net.minis.aa.domain.Role;
import net.minis.aa.domain.RoleGroup;
import net.minis.aa.domain.User;

public interface RoleGroupService {

    RoleGroup saveRoleGroup(RoleGroup roleGroup);

    void saveRoleGroups(User user, List<Role> roles);

    void deleteRoleGroup(Long id);

    void deleteRoleGroupsByUsername(String username);

    void deleteRoleGroupsByRoleId(String roleId);

    List<RoleGroup> findAllRoleGroups();

    List<RoleGroup> findRoleGroupsByUsername(String username);

    List<RoleGroup> findRoleGroupsByRoleId(String roleId);

    RoleGroup getRoleGroup(String username, String roleId);

}
