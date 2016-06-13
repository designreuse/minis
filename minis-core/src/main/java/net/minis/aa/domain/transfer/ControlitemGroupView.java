package net.minis.aa.domain.transfer;

import java.util.List;

import lombok.Data;
import net.minis.aa.domain.Controlitem;

@Data
public class ControlitemGroupView {

    private List<Controlitem> controlitems;

    private List<Controlitem> controlitemGroups;

}
