package net.minis.aa.form.convert;

import java.util.List;

import net.minis.aa.domain.Role;
import net.minis.aa.form.RoleGroupForm;
import net.minis.aa.service.RoleService;
import net.minis.api.convert.ObjectConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class RoleGroupFormConverter extends ObjectConverter {

    @Autowired
    private RoleService roleService;

    public List<Role> convert(RoleGroupForm source) {

        List<Role> target = Lists.newArrayList();

        // convert role id -> roles.
        List<String> roleIds = source.getRoles();

        for (String roleId : roleIds) {
            Role role = roleService.getRoleById(roleId);
            target.add(role);
        }

        return target;
    }

}
