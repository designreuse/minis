package net.minis.aa.service.impl;

import java.util.List;

import net.minis.aa.domain.User;
import net.minis.aa.repository.UserDao;
import net.minis.aa.service.RoleGroupService;
import net.minis.aa.service.UserService;
import net.minis.api.spring.data.SearchCondition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleGroupService roleGroupsService;

    @Override
    public User saveUser(User user) {
        return userDao.saveAndFlush(user);
    }

    @Override
    public void deleteUser(String id) {
        User user = getUserById(id);
//        roleGroupsService.deleteRoleGroupsByUsername(user.getUsername());
        userDao.delete(user);
    }

    @Override
    public void deleteUserByUsername(String username) {
        userDao.deleteByUsername(username);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<User> searchUsers(SearchCondition condition) {
        Specification<User> specification = condition.getSpecification();
        Pageable pageable = condition.getPageable();
        return userDao.findAll(specification, pageable);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll(new Sort(Sort.Direction.ASC, "username"));
    }

    @Override
    public User getUserById(String id) {
        return (id == null) ? null : userDao.findOne(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return (username == null) ? null : userDao.getByUsername(username);
    }

}
