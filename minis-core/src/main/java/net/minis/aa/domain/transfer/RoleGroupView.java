package net.minis.aa.domain.transfer;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.minis.aa.domain.Role;

@Getter
@Setter
public class RoleGroupView {

    private List<Role> roles;

    private List<Role> roleGroups;

}
