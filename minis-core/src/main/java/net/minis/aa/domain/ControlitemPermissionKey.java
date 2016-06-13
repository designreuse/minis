package net.minis.aa.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.minis.api.spring.data.BaseModel;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = { "role", "controlitem" })
public class ControlitemPermissionKey implements BaseModel {

    private static final long serialVersionUID = 1L;

    private String role;

    private String controlitem;

    public ControlitemPermissionKey(String role, String controlitem) {
        this.role = role;
        this.controlitem = controlitem;
    }

}
