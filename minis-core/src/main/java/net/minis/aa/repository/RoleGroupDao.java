package net.minis.aa.repository;

import java.util.List;

import net.minis.aa.domain.RoleGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoleGroupDao extends JpaRepository<RoleGroup, Long> {

    @Modifying
    @Query("DELETE RoleGroup WHERE role.id = ?1)")
    void deleteByRoleId(String roleId);

    @Modifying
    @Query("DELETE RoleGroup WHERE user IN (SELECT u.username FROM User u WHERE u.username = ?1)")
    void deleteByUsername(String username);

    @Query("SELECT relation FROM RoleGroup relation WHERE user.username = ?1 AND role.id = ?2")
    RoleGroup getByUsernameAndRoleId(String username, String roleId);

    @Query("SELECT relation FROM RoleGroup relation WHERE user.username = ?1 ORDER BY role.name ASC")
    List<RoleGroup> findByUsername(String username);

    @Query("SELECT relation FROM RoleGroup relation WHERE role.id = ?1 ORDER BY role.name ASC")
    List<RoleGroup> findByRoleId(String roleId);

}
