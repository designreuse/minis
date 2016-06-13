package net.minis.aa.service;

import java.util.List;

import net.minis.aa.domain.User;
import net.minis.api.spring.data.SearchCondition;

import org.springframework.data.domain.Page;

public interface UserService {

    User saveUser(User user);

    void deleteUser(String id);

    void deleteUserByUsername(String username);

    Page<User> searchUsers(SearchCondition condition);

    List<User> findAllUsers();

    User getUserById(String id);

    User getUserByUsername(String username);

}
