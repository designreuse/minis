package net.minis.aa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.annotation.Resource;

import net.minis.aa.domain.Controlitem;
import net.minis.aa.domain.ControlitemPermission;
import net.minis.aa.domain.Role;
import net.minis.aa.domain.transfer.RoleGroupView;
import net.minis.aa.form.RoleGroupForm;
import net.minis.aa.form.convert.RoleGroupFormConverter;
import net.minis.aa.service.ControlitemPermissionService;
import net.minis.aa.service.ControlitemService;
import net.minis.aa.service.RoleService;
import net.minis.api.spring.webmvc.exception.NotFoundException;

import org.apache.commons.collections.ListUtils;
import org.assertj.core.util.Lists;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Transactional
@RequestMapping("/aa")
public class ControlitemPermissionController {

    @Resource
    private RoleService roleService;

    @Resource
    private ControlitemService controlitemService;

    @Resource
    private ControlitemPermissionService permissionService;

    @Resource
    private RoleGroupFormConverter permissionFormConverter;


    @RequestMapping(value = "/controlitem-permission.html", method = GET)
    public ModelAndView indexPage() {
        ModelAndView model = new ModelAndView("/aa/pages/controlitem-permission.jsp");
        return model;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/controlitem/permission/findByControlitemId/{controlitemId}", method = GET)
    public RoleGroupView findPermissionsByControlitemId(
            @PathVariable("controlitemId") String controlitemId) {

        List<ControlitemPermission> permissions = 
                permissionService.findPermissionsByControlitemId(controlitemId);

        List<Role> controlitemRoles = Lists.newArrayList();
        for (ControlitemPermission permission : permissions) {
            controlitemRoles.add(permission.getRole());
        }

        List<Role> allRoles = roleService.findAllRoles();
        List<Role> subtractRoles = ListUtils.subtract(allRoles, controlitemRoles);

        RoleGroupView result = new RoleGroupView();
        result.setRoles(subtractRoles);
        result.setRoleGroups(controlitemRoles);
        return result;
    }

    @RequestMapping(value = "/controlitem/permission/updateByControlitemId/{controlitemId}", method = POST)
    public void updatePermissionsByRole(
            @PathVariable("controlitemId") String controlitemId,
            @RequestBody RoleGroupForm request) {

        Controlitem controlitem = controlitemService.getControlitemById(controlitemId);

        if (controlitem == null) {
            throw new NotFoundException();
        }

        List<Role> roles = permissionFormConverter.convert(request);
        permissionService.savePermissions(controlitem, roles);
    }

}
