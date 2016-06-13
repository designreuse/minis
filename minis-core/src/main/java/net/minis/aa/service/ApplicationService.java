package net.minis.aa.service;

import java.util.List;

import net.minis.aa.domain.Application;
import net.minis.api.spring.data.SearchCondition;

import org.springframework.data.domain.Page;

public interface ApplicationService {

    Application saveApplication(Application application);

    void deleteApplication(String id);

    Page<Application> searchApplications(SearchCondition condition);

    long countAllApplications();

    List<Application> findAllApplications();

    List<Application> findApplicationsByRoleId(String roleId);

    Application getApplicationById(String id);

}
