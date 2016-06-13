package net.minis.aa.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.annotation.Resource;

import net.minis.aa.domain.User;
import net.minis.aa.form.UserForm;
import net.minis.aa.form.convert.UserFormConverter;
import net.minis.aa.service.UserService;
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
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserFormConverter userFormConverter;

    @RequestMapping(value = { "/user.html" }, method = GET)
    public ModelAndView indexPage() {
        ModelAndView model = new ModelAndView("/aa/pages/user.jsp");
        return model;
    }

    @RequestMapping(value = "/user/createUser", method = POST)
    public @ResponseBody User createUser(
            @RequestBody @Validated(create.class) UserForm form) {

        User user = userFormConverter.convert(form);
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/user/updateUser", method = POST)
    public @ResponseBody User updateUser(
            @RequestBody @Validated(update.class) UserForm form) {

        String id = form.getId();
        User user = userService.getUserById(id);

        user = userFormConverter.convert(form, user);
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/user/deleteUser", method = POST)
    public void deleteUser(@RequestBody UserForm form) {
        String id = form.getId();
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/user/searchUser", method = POST)
    public @ResponseBody GridView searchUser(@RequestBody FilterForm form) {

        MappedSearchCondition searchCondition = new MappedSearchCondition();
        searchCondition.setPage(form.page);
        searchCondition.setPageSize(form.pageSize);
        searchCondition.setSort(new Sort(Direction.ASC, "username"));

        Page<User> users = userService.searchUsers(searchCondition);

        GridView response = new GridView();
        response.setPage(form.page);
        response.setPageSize(form.pageSize);
        response.setTotal(users.getTotalElements());
        response.setTotalPages(users.getTotalPages());
        response.setContents(users.getContent());

        return response;
    }

}
