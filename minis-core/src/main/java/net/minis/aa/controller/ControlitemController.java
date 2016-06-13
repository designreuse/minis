package net.minis.aa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.annotation.Resource;

import net.minis.aa.domain.Controlitem;
import net.minis.aa.form.ControlitemForm;
import net.minis.aa.form.convert.ControlitemFormConverter;
import net.minis.aa.service.ControlitemService;
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
public class ControlitemController {

    @Resource
    private ControlitemService controlitemService;

    @Resource
    private ControlitemFormConverter controlitemFormConverter;

    @RequestMapping(value = "/controlitem.html", method = GET)
    public ModelAndView indexPage() {
        ModelAndView model = new ModelAndView("/aa/pages/controlitem.jsp");
        return model;
    }

    @RequestMapping(value = "/controlitem/createControlitem", method = POST)
    public @ResponseBody Controlitem createControlitem(
            @RequestBody ControlitemForm form) {

        Controlitem controlitem = controlitemFormConverter.convert(form);
        return controlitemService.saveControlitem(controlitem);
    }

    @RequestMapping(value = "/controlitem/updateControlitem", method = POST)
    public @ResponseBody Controlitem updateControlitem(
            @RequestBody ControlitemForm form) {

        String id = form.getId();
        Controlitem controlitem = controlitemService.getControlitemById(id);

        if (controlitem == null) {
            controlitem = new Controlitem();
        }

        controlitem = controlitemFormConverter.convert(form, controlitem);
        return controlitemService.saveControlitem(controlitem);
    }
    
    @RequestMapping(value = "/controlitem/deleteControlitem", method = POST)
    public void deleteControlitem(@RequestBody ControlitemForm form) {
        Validate.notNull(form);
        Validate.notNull(form.getId());
        controlitemService.deleteControlitem(form.getId());
    }

    @RequestMapping(value = "/controlitem/searchControlitems", method = POST)
    public @ResponseBody GridView searchControlitems(@RequestBody FilterForm form) {

        MappedSearchCondition searchCondition = new MappedSearchCondition();
        searchCondition.setPage(form.page);
        searchCondition.setPageSize(form.pageSize);

        searchCondition.setSort(new Sort(Direction.ASC, "id"));
        
        Page<Controlitem> results = controlitemService.searchControlitems(searchCondition);

        GridView response = new GridView();
        response.setPage(form.page);
        response.setPageSize(form.pageSize);
        response.setTotal(results.getTotalElements());
        response.setTotalPages(results.getTotalPages());
        response.setContents(results.getContent());

        return response;
    }

}
