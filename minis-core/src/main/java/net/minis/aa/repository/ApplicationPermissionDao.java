package net.minis.aa.repository;

import java.util.List;

import net.minis.aa.domain.ApplicationPermission;
import net.minis.aa.domain.ApplicationPermissionKey;
import net.minis.api.spring.data.BaseRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationPermissionDao extends
        BaseRepository<ApplicationPermission, ApplicationPermissionKey> {

    @Modifying
    @Query("DELETE FROM ApplicationPermission ap WHERE role.id = ?1")
    void deleteByRoleId(String roleId);

    @Modifying
    @Query("DELETE FROM ApplicationPermission ap WHERE application.id = ?1")
    void deleteByApplicationId(String applicationId);

    @Query("SELECT COUNT(ap) FROM ApplicationPermission ap WHERE role.id IN ?1 AND application.id = ?2")
    Long count(List<String> roleId, String actionId);

    @Query("SELECT ap FROM ApplicationPermission ap WHERE role.id = ?1 ORDER BY application ASC")
    List<ApplicationPermission> findByRoleId(String roleId);

    @Query("SELECT ap FROM ApplicationPermission ap WHERE application.id = ?1 ORDER BY role ASC")
    List<ApplicationPermission> findByApplicationId(String applicationId);

}
