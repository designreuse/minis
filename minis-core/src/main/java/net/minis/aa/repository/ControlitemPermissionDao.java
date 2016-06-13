package net.minis.aa.repository;

import java.util.List;

import net.minis.aa.domain.ControlitemPermission;
import net.minis.aa.domain.ControlitemPermissionKey;
import net.minis.api.spring.data.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ControlitemPermissionDao extends
        BaseRepository<ControlitemPermission, ControlitemPermissionKey> {

    @Modifying
    @Query("DELETE FROM ControlitemPermission ap WHERE role.id = ?1")
    void deleteByRoleId(String roleId);

    @Modifying
    @Query("DELETE FROM ControlitemPermission ap WHERE controlitem.id = ?1")
    void deleteByControlitemId(String controlitemId);

    @Query("SELECT COUNT(cp) FROM ControlitemPermission cp WHERE role.id IN ?1 AND controlitem.id = ?2")
    Long count(List<String> roleId, String controlId);

    @Query("SELECT cp FROM ControlitemPermission cp WHERE role.id = ?1 ORDER BY controlitem ASC")
    List<ControlitemPermission> findByRoleId(String roleId);

    @Query("SELECT cp FROM ControlitemPermission cp WHERE controlitem.id = ?1 ORDER BY role ASC")
    List<ControlitemPermission> findByControlitemId(String controlitemId);

}
