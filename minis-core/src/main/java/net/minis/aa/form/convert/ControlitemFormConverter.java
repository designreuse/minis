package net.minis.aa.form.convert;

import net.minis.aa.domain.Controlitem;
import net.minis.aa.form.ControlitemForm;
import net.minis.api.convert.ModelConverter;

import org.springframework.stereotype.Component;

@Component
public class ControlitemFormConverter extends
        ModelConverter<ControlitemForm, Controlitem> {

    @Override
    public Controlitem convert(ControlitemForm source, Controlitem target) {

        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());

        return target;
    }

}
