package net.minis.aa.form;

import java.io.Serializable;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

@Data
public class RoleForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    private String description;

}
