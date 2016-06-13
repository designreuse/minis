package net.minis.aa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.annotation.Resource;

import net.minis.aa.domain.Application;
import net.minis.aa.domain.ApplicationPermission;
import net.minis.aa.domain.Role;
import net.minis.aa.domain.transfer.RoleGroupView;
import net.minis.aa.form.RoleGroupForm;
import net.minis.aa.form.convert.RoleGroupFormConverter;
import net.minis.aa.service.ApplicationPermissionService;
import net.minis.aa.service.ApplicationService;
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
public class ApplicationPermissionController {

    @Resource
    private RoleService roleService;

    @Resource
    private ApplicationService applicationService;

    @Resource
    private ApplicationPermissionService permissionService;

    @Resource
    private RoleGroupFormConverter permissionFormConverter;

    @RequestMapping(value = "/application-permission.html", method = GET)
    public ModelAndView indexPage() {
        ModelAndView model = new ModelAndView("/aa/pages/application-permission.jsp");
        return model;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/application/permission/findByApplicationId/{applicationId}", method = GET)
    public RoleGroupView findPermissionsByApplicationId(
            @PathVariable("applicationId") String applicationId) {

        List<ApplicationPermission> permissions = 
                permissionService.findPermissionsByApplicationId(applicationId);

        List<Role> applicationRoles = Lists.newArrayList();
        for (ApplicationPermission permission : permissions) {
            applicationRoles.add(permission.getRole());
        }

        List<Role> allRoles = roleService.findAllRoles();
        List<Role> subtractRoles = ListUtils.subtract(allRoles, applicationRoles);

        RoleGroupView result = new RoleGroupView();
        result.setRoles(subtractRoles);
        result.setRoleGroups(applicationRoles);
        return result;
    }

    @RequestMapping(value = "/application/permission/updateByApplicationId/{applicationId}", method = POST)
    public void updatePermissionsByRole(
            @PathVariable("applicationId") String applicationId,
            @RequestBody RoleGroupForm request) {

        Application application = applicationService.getApplicationById(applicationId);

        if (application == null) {
            throw new NotFoundException();
        }

        List<Role> roles = permissionFormConverter.convert(request);
        permissionService.savePermissions(application, roles);
    }

}
