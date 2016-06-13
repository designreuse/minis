package net.minis.aa.form;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class RoleGroupForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> roles;

}
