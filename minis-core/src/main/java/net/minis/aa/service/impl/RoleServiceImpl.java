package net.minis.aa.service.impl;

import java.util.List;

import net.minis.aa.domain.Role;
import net.minis.aa.domain.RoleGroup;
import net.minis.aa.repository.RoleDao;
import net.minis.aa.service.RoleGroupService;
import net.minis.aa.service.RoleService;
import net.minis.api.spring.data.SearchCondition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleGroupService userRoleService;

    @Override
    public Role saveRole(Role role) {
        return roleDao.save(role);
    }

    @Override
    public void deleteRole(String id) {
        userRoleService.deleteRoleGroupsByRoleId(id);
        roleDao.delete(id);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleDao.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Role> findRolesByUsername(String username) {

        List<RoleGroup> userRoles = userRoleService.findRoleGroupsByUsername(username);

        List<Role> roles = Lists.transform(userRoles, new Function<RoleGroup, Role>() {
            public Role apply(RoleGroup input) {
                return input.getRole();
            }
        });

        return roles;
    }

    @Override
    public Role getRoleById(String id) {

        if (id == null) {
            return null;
        }

        return roleDao.findOne(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<Role> searchRoles(SearchCondition searchCondition) {
        Specification<Role> specification = searchCondition.getSpecification();
        Pageable pageable = searchCondition.getPageable();
        return roleDao.findAll(specification, pageable);
    }

}
