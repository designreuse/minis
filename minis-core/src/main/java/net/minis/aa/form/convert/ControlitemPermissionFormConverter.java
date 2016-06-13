package net.minis.aa.form.convert;

import java.util.List;

import net.minis.aa.domain.Controlitem;
import net.minis.aa.form.ControlitemPermissionForm;
import net.minis.aa.service.ControlitemPermissionService;
import net.minis.aa.service.ControlitemService;
import net.minis.api.convert.ObjectConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class ControlitemPermissionFormConverter extends ObjectConverter {

    @Autowired
    private ControlitemService controlitemService;

    @Autowired
    private ControlitemPermissionService controlitemPermissionService;

    public List<Controlitem> convert(ControlitemPermissionForm source) {

        List<Controlitem> target = Lists.newArrayList();

        // convert controlitem ids -> controlitems.
        List<String> controlitemIds = source.getControlitems();

        for (String controlitemId : controlitemIds) {
            Controlitem controlitem = controlitemService.getControlitemById(controlitemId);
            target.add(controlitem);
        }

        return target;
    }

}
