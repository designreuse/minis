package net.minis.aa.service.impl;

import java.util.List;

import net.minis.aa.domain.Application;
import net.minis.aa.domain.ApplicationPermission;
import net.minis.aa.repository.ApplicationDao;
import net.minis.aa.repository.ApplicationPermissionDao;
import net.minis.aa.service.ApplicationService;
import net.minis.api.spring.data.SearchCondition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private ApplicationPermissionDao applicationPermissionDao;

    @Override
    public Application saveApplication(Application application) {
        return applicationDao.save(application);
    }

    @Override
    public void deleteApplication(String id) {
        applicationDao.delete(id);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Page<Application> searchApplications(SearchCondition condition) {
        Pageable pageable = condition.getPageable();
        Specification specification = condition.getSpecification();
        return applicationDao.findAll(specification, pageable);
    }

    @Override
    public List<Application> findAllApplications() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return applicationDao.findAll(sort);
    }

    @Override
    public long countAllApplications() {
        return applicationDao.count();
    }

    @Override
    public List<Application> findApplicationsByRoleId(String roleId) {

        List<ApplicationPermission> permissions = applicationPermissionDao.findByRoleId(roleId);

        List<Application> applications = 
            Lists.transform(permissions,new Function<ApplicationPermission, Application>() {
                public Application apply(ApplicationPermission input) {
                    return input.getApplication();
                }
            });

        return applications;
    }

    @Override
    public Application getApplicationById(String id) {
        return (id == null) ? null : applicationDao.findOne(id);
    }

}
