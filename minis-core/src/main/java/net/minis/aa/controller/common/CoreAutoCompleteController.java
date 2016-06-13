package net.minis.aa.controller.common;

import java.util.List;

import net.minis.aa.domain.Application;
import net.minis.aa.domain.Controlitem;
import net.minis.aa.domain.Role;
import net.minis.aa.domain.User;
import net.minis.aa.service.ApplicationService;
import net.minis.aa.service.ControlitemService;
import net.minis.aa.service.RoleService;
import net.minis.aa.service.UserService;
import net.minis.api.spring.data.MappedSearchCondition;
import net.minis.api.web.kendo.AutoCompleteView;
import net.minis.api.web.kendo.FilterForm;
import net.minis.api.web.kendo.FilterFormConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/aa/autocomplate")
public class CoreAutoCompleteController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ControlitemService controlitemService;

    private MappedSearchCondition convertToSearchCondition(FilterForm form) {
        FilterFormConverter formConverter = new FilterFormConverter();
        MappedSearchCondition searchCondition = formConverter.convert(form);
        return searchCondition;
    }

    private AutoCompleteView convertToAutoComplateView(Page<?> pageData) {
        List<?> content = pageData.getContent();
        long totalPages = pageData.getTotalPages();
        long totalElements = pageData.getTotalElements();
        return new AutoCompleteView(content, totalElements, totalPages);
    }

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public AutoCompleteView userAutoComplate(@RequestBody FilterForm form) {

        MappedSearchCondition searchCondition = convertToSearchCondition(form);

        // Set default sort condition.
        if (searchCondition.getSort() == null) {
            searchCondition.setSort(new Sort(Sort.Direction.ASC, "username"));
        }

        Page<User> result = userService.searchUsers(searchCondition);
        return convertToAutoComplateView(result);
    }

    @ResponseBody
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public AutoCompleteView roleAutoComplate(@RequestBody FilterForm form) {

        MappedSearchCondition searchCondition = convertToSearchCondition(form);

        // Set default sort condition.
        if (searchCondition.getSort() == null) {
            searchCondition.setSort(new Sort(Sort.Direction.ASC, "id"));
        }

        Page<Role> result = roleService.searchRoles(searchCondition);
        return convertToAutoComplateView(result);
    }

    @ResponseBody
    @RequestMapping(value = "/application", method = RequestMethod.POST)
    public AutoCompleteView applicationAutoComplate(@RequestBody FilterForm form) {

        MappedSearchCondition searchCondition = convertToSearchCondition(form);

        // Set default sort condition.
        if (searchCondition.getSort() == null) {
            searchCondition.setSort(new Sort(Sort.Direction.ASC, "id"));
        }

        Page<Application> result = applicationService.searchApplications(searchCondition);
        return convertToAutoComplateView(result);
    }

    @ResponseBody
    @RequestMapping(value = "/controlitem", method = RequestMethod.POST)
    public AutoCompleteView controlitemAutoComplate(@RequestBody FilterForm form) {

        MappedSearchCondition searchCondition = convertToSearchCondition(form);

        // Set default sort condition.
        if (searchCondition.getSort() == null) {
            searchCondition.setSort(new Sort(Sort.Direction.ASC, "id"));
        }

        Page<Controlitem> result = controlitemService.searchControlitems(searchCondition);
        return convertToAutoComplateView(result);
    }

}
