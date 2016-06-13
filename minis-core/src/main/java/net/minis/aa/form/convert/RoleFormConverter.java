package net.minis.aa.form.convert;

import javax.annotation.Resource;

import net.minis.aa.domain.Role;
import net.minis.aa.form.RoleForm;
import net.minis.aa.service.RoleService;
import net.minis.api.convert.ModelConverter;

import org.springframework.stereotype.Component;

@Component
public class RoleFormConverter extends ModelConverter<RoleForm, Role> {

    @Resource
    public RoleService roleService;

    public Role convert(RoleForm source, Role target) {

        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());

        return target;
    }

}
