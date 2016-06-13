package net.minis.aa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.annotation.Resource;

import net.minis.aa.domain.Controlitem;
import net.minis.aa.domain.Role;
import net.minis.aa.domain.transfer.ControlitemGroupView;
import net.minis.aa.form.ControlitemPermissionForm;
import net.minis.aa.form.convert.ControlitemPermissionFormConverter;
import net.minis.aa.service.ControlitemPermissionService;
import net.minis.aa.service.ControlitemService;
import net.minis.aa.service.RoleService;
import net.minis.api.spring.webmvc.exception.NotFoundException;

import org.apache.commons.collections.ListUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Transactional
@RequestMapping("/aa")
public class ControlitemPermission2Controller {

    @Resource
    private RoleService roleService;

    @Resource
    private ControlitemService controlitemService;

    @Resource
    private ControlitemPermissionService permissionService;

    @Resource
    private ControlitemPermissionFormConverter permissionFormConverter;

    @RequestMapping(value = "/controlitem-permission2.html", method = GET)
    public ModelAndView indexPage() {
        ModelAndView model = new ModelAndView("/aa/pages/controlitem-permission2.jsp");
        return model;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/controlitem/permission/findByRoleId/{roleId}", method = GET)
    public ControlitemGroupView findPermissionsByRoleId(
            @PathVariable("roleId") String roleId) {

        List<Controlitem> allControlitems = controlitemService.findAllControlitems();
        List<Controlitem> controlitemGroups = controlitemService.findControlitemsByRoleId(roleId);
        List<Controlitem> subtractControlitems = ListUtils.subtract(allControlitems, controlitemGroups);

        ControlitemGroupView result = new ControlitemGroupView();
        result.setControlitems(subtractControlitems);
        result.setControlitemGroups(controlitemGroups);
        return result;
    }

    @RequestMapping(value = "/controlitem/permission/updateByRoleId/{roleId}", method = POST)
    public void updatePermissionsByRole(
            @PathVariable("roleId") String roleId,
            @RequestBody ControlitemPermissionForm request) {

        Role role = roleService.getRoleById(roleId);

        if (role == null) {
            throw new NotFoundException();
        }

        List<Controlitem> controlitems = permissionFormConverter.convert(request);
        permissionService.savePermissions(role, controlitems);
    }

}
