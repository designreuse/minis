package net.minis.aa.repository;

import net.minis.aa.domain.User;
import net.minis.api.spring.data.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends BaseRepository<User, String> {

    @Modifying
    @Query("DELETE User WHERE username = ?1)")
    void deleteByUsername(String username);

	User getByUsername(String username);

}
