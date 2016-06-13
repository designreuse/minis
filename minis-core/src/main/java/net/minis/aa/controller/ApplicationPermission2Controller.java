package net.minis.aa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.annotation.Resource;

import net.minis.aa.domain.Application;
import net.minis.aa.domain.Role;
import net.minis.aa.domain.transfer.ApplicationGroupView;
import net.minis.aa.form.ApplicationGroupsForm;
import net.minis.aa.form.convert.ApplicationPermissionFormConverter;
import net.minis.aa.service.ApplicationPermissionService;
import net.minis.aa.service.ApplicationService;
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
public class ApplicationPermission2Controller {

    @Resource
    private RoleService roleService;

    @Resource
    private ApplicationService applicationService;

    @Resource
    private ApplicationPermissionService permissionService;

    @Resource
    private ApplicationPermissionFormConverter permissionFormConverter;

    @RequestMapping(value = "/application-permission2.html", method = GET)
    public ModelAndView indexPage() {
        ModelAndView model = new ModelAndView(
                "/core/pages/application-permission2.jsp");
        return model;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/application/permission/findByRoleId/{roleId}", method = GET)
    public ApplicationGroupView findPermissionsByRoleId(
            @PathVariable("roleId") String roleId) {

        List<Application> allApplications = applicationService
                .findAllApplications();
        List<Application> applicationsGroups = applicationService
                .findApplicationsByRoleId(roleId);
        List<Application> subtractApplications = ListUtils.subtract(
                allApplications, applicationsGroups);

        ApplicationGroupView result = new ApplicationGroupView();
        result.setApplications(subtractApplications);
        result.setApplicationsGroups(applicationsGroups);
        return result;
    }

    @RequestMapping(value = "/application/permission/updateByRoleId/{roleId}", method = POST)
    public void updatePermissionsByRole(
            @PathVariable("roleId") String roleId,
            @RequestBody ApplicationGroupsForm request) {

        Role role = roleService.getRoleById(roleId);

        if (role == null) {
            throw new NotFoundException();
        }

        List<Application> applications = permissionFormConverter
                .convert(request);
        permissionService.savePermissions(role, applications);
    }

}
