package net.minis.aa.service;

import java.util.List;

import net.minis.aa.domain.Role;
import net.minis.api.spring.data.SearchCondition;

import org.springframework.data.domain.Page;

public interface RoleService {

    Role saveRole(Role role);

    void deleteRole(String id);

    Page<Role> searchRoles(SearchCondition searchCondition);

    List<Role> findAllRoles();

    List<Role> findRolesByUsername(String username);

    Role getRoleById(String id);

}
