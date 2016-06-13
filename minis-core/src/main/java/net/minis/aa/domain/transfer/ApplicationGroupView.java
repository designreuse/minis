package net.minis.aa.domain.transfer;

import java.util.List;

import lombok.Data;
import net.minis.aa.domain.Application;

@Data
public class ApplicationGroupView {

    private List<Application> applications;

    private List<Application> applicationsGroups;

}
