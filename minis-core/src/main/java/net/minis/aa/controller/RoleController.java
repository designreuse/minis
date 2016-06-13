package net.minis.aa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.annotation.Resource;

import net.minis.aa.domain.Role;
import net.minis.aa.form.RoleForm;
import net.minis.aa.form.convert.RoleFormConverter;
import net.minis.aa.service.RoleService;
import net.minis.api.spring.data.MappedSearchCondition;
import net.minis.api.validation.groups.create;
import net.minis.api.validation.groups.update;
import net.minis.api.web.FilterForm;
import net.minis.api.web.GridView;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Transactional
@RequestMapping("/aa")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private RoleFormConverter roleFormConverter;

    @RequestMapping(value = { "/role.html" }, method = GET)
    public ModelAndView indexPage() {
        ModelAndView model = new ModelAndView("/aa/pages/role.jsp");
        return model;
    }

    @RequestMapping(value = "/role/createRole", method = POST)
    public @ResponseBody Role createRole(
            @RequestBody @Validated(create.class) RoleForm form) {

        Role role = roleFormConverter.convert(form);
        return roleService.saveRole(role);
    }

    @RequestMapping(value = "/role/updateRole", method = POST)
    public @ResponseBody Role updateRole(
            @RequestBody @Validated(update.class) RoleForm form) {

        String id = form.getId();
        Role role = roleService.getRoleById(id);

        if (role == null) {
            role = new Role();
        }

        role = roleFormConverter.convert(form, role);
        return roleService.saveRole(role);
    }

    @RequestMapping(value = "/role/deleteRole", method = POST)
    public void deleteRole(@RequestBody RoleForm form) {
        String id = form.getId();
        roleService.deleteRole(id);
    }

    @RequestMapping(value = "/role/searchRole", method = POST)
    public @ResponseBody GridView searchRole(@RequestBody FilterForm form) {

        MappedSearchCondition searchCondition = new MappedSearchCondition();
        searchCondition.setPage(form.page);
        searchCondition.setPageSize(form.pageSize);
        searchCondition.setSort(new Sort(Direction.ASC, "id"));
        
        Page<Role> roles = roleService.searchRoles(searchCondition);

        GridView response = new GridView();
        response.setPage(form.page);
        response.setPageSize(form.pageSize);
        response.setTotal(roles.getTotalElements());
        response.setTotalPages(roles.getTotalPages());
        response.setContents(roles.getContent());

        return response;
    }

}
