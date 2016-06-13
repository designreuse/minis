package net.minis.aa.form.convert;

import net.minis.aa.domain.Application;
import net.minis.aa.form.ApplicationForm;
import net.minis.api.convert.ModelConverter;

import org.springframework.stereotype.Component;

@Component
public class ApplicationFormConverter extends
        ModelConverter<ApplicationForm, Application> {

    @Override
    public Application convert(ApplicationForm source, Application target) {

        target.setId(source.getId());
        target.setName(source.getName());
        target.setPath(source.getPath());
        target.setDescription(source.getDescription());

        return target;
    }

}
