package net.minis.aa.form.convert;

import java.util.List;

import net.minis.aa.domain.Application;
import net.minis.aa.form.ApplicationGroupsForm;
import net.minis.aa.service.ApplicationService;
import net.minis.api.convert.ObjectConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class ApplicationPermissionFormConverter extends ObjectConverter {

    @Autowired
    private ApplicationService applicationService;

    public List<Application> convert(ApplicationGroupsForm source) {

        List<Application> target = Lists.newArrayList();

        // convert application ids -> applications.
        List<String> applicationIds = source.getApplications();

        for (String applicationId : applicationIds) {
            Application application = applicationService
                    .getApplicationById(applicationId);
            target.add(application);
        }

        return target;
    }

}
