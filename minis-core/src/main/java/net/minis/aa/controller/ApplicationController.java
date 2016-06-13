package net.minis.aa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.annotation.Resource;

import net.minis.aa.domain.Application;
import net.minis.aa.form.ApplicationForm;
import net.minis.aa.form.convert.ApplicationFormConverter;
import net.minis.aa.service.ApplicationService;
import net.minis.api.spring.data.MappedSearchCondition;
import net.minis.api.web.FilterForm;
import net.minis.api.web.GridView;

import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Transactional
@RequestMapping("/aa")
public class ApplicationController {

    @Resource
    private ApplicationService applicationService;

    @Resource
    private ApplicationFormConverter applicationFormConverter;

    @RequestMapping(value = "/application.html", method = GET)
    public ModelAndView indexPage() {
        ModelAndView model = new ModelAndView("/aa/pages/application.jsp");
        return model;
    }
    
    @RequestMapping(value = "/application/createApp", method = POST)
    public @ResponseBody Application createApplication(@RequestBody ApplicationForm form) {

        Application application = applicationFormConverter.convert(form);
        return applicationService.saveApplication(application);
    }

    @RequestMapping(value = "/application/updateApp", method = POST)
    public @ResponseBody Application updateApplication(@RequestBody ApplicationForm form) {

        String id = form.getId();
        Application application = applicationService.getApplicationById(id);

        if (application == null) {
            application = new Application();
        }

        application = applicationFormConverter.convert(form, application);
        return applicationService.saveApplication(application);
    }
    
    @RequestMapping(value = "/application/deleteApp", method = POST)
    public void deleteApplication(@RequestBody ApplicationForm form) {
        Validate.notNull(form);
        Validate.notNull(form.getId());
        applicationService.deleteApplication(form.getId());
    }

    @RequestMapping(value = "/application/searchApp", method = POST)
    public @ResponseBody GridView searchApplication(@RequestBody FilterForm form) {

        MappedSearchCondition searchCondition = new MappedSearchCondition();
        searchCondition.setPage(form.page);
        searchCondition.setPageSize(form.pageSize);
        searchCondition.setSort(new Sort(Direction.ASC, "id"));

        Page<Application> results = applicationService.searchApplications(searchCondition);

        GridView response = new GridView();
        response.setPage(form.page);
        response.setPageSize(form.pageSize);
        response.setTotal(results.getTotalElements());
        response.setTotalPages(results.getTotalPages());
        response.setContents(results.getContent());

        return response;
    }

}
