package net.minis.aa.service.impl;

import java.util.List;

import net.minis.aa.domain.Role;
import net.minis.aa.domain.RoleGroup;
import net.minis.aa.domain.User;
import net.minis.aa.repository.RoleGroupDao;
import net.minis.aa.service.RoleGroupService;
import net.minis.aa.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RoleGroupServiceImpl implements RoleGroupService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleGroupDao roleGroupDao;

    @Override
    public RoleGroup saveRoleGroup(RoleGroup roleGroup) {
        return roleGroupDao.save(roleGroup);
    }

    @Override
    public void saveRoleGroups(User user, List<Role> roles) {

        // save the user and role relation.
        String username = user.getUsername();
        this.deleteRoleGroupsByUsername(username);

        for (Role role : roles) {
            RoleGroup roleGroup = new RoleGroup();
            roleGroup.setUser(user);
            roleGroup.setRole(role);
            this.saveRoleGroup(roleGroup);
        }
    }

    @Override
    public void deleteRoleGroup(Long id) {
        roleGroupDao.delete(id);
    }

    @Override
    public void deleteRoleGroupsByUsername(String username) {
        roleGroupDao.deleteByUsername(username);
    }

    @Override
    public void deleteRoleGroupsByRoleId(String roleId) {
        roleGroupDao.deleteByRoleId(roleId);
    }

    @Override
    public List<RoleGroup> findAllRoleGroups() {
        return roleGroupDao.findAll(new Sort(Sort.Direction.ASC, "username"));
    }

    @Override
    public List<RoleGroup> findRoleGroupsByUsername(String username) {
        return roleGroupDao.findByUsername(username);
    }

    @Override
    public List<RoleGroup> findRoleGroupsByRoleId(String roleId) {
        return roleGroupDao.findByRoleId(roleId);
    }

    @Override
    public RoleGroup getRoleGroup(String username, String roleId) {
        return roleGroupDao.getByUsernameAndRoleId(username, roleId);
    }

}
