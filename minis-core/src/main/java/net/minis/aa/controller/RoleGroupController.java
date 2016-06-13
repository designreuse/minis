package net.minis.aa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.annotation.Resource;

import net.minis.aa.domain.Role;
import net.minis.aa.domain.User;
import net.minis.aa.domain.transfer.RoleGroupView;
import net.minis.aa.form.RoleGroupForm;
import net.minis.aa.form.convert.RoleGroupFormConverter;
import net.minis.aa.service.RoleGroupService;
import net.minis.aa.service.RoleService;
import net.minis.aa.service.UserService;
import net.minis.api.spring.webmvc.exception.NotFoundException;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Transactional
@RequestMapping("/aa")
public class RoleGroupController {

    @Autowired
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private RoleGroupService roleGroupService;

    @Resource
    private RoleGroupFormConverter roleGroupFormConverter;

    @RequestMapping(value = "/role-group.html", method = GET)
    public ModelAndView roleGroupPage() {
        ModelAndView model = new ModelAndView("/aa/pages/role-group.jsp");
        return model;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/role/group/findByUsername/{username}", method = GET)
    public RoleGroupView findRoleGroupsByUsername(
            @PathVariable("username") String username) {

        List<Role> allRoles = roleService.findAllRoles();
        List<Role> roleGroups = roleService.findRolesByUsername(username);
        List<Role> subtractRoles = ListUtils.subtract(allRoles, roleGroups);

        RoleGroupView result = new RoleGroupView();
        result.setRoles(subtractRoles);
        result.setRoleGroups(roleGroups);
        return result;
    }

    @RequestMapping(value = "/role/group/updateByUsername/{username}", method = POST)
    public void updatePermissionsByRole(
            @PathVariable("username") String username,
            @RequestBody RoleGroupForm request) {

        User user = userService.getUserByUsername(username);

        if (user == null) {
            throw new NotFoundException();
        }

        List<Role> roles = roleGroupFormConverter.convert(request);
        roleGroupService.saveRoleGroups(user, roles);
    }

}
