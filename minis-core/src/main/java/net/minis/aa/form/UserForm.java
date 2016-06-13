package net.minis.aa.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import net.minis.aa.validation.constraints.NotDuplicateUser;
import net.minis.api.validation.groups.create;
import net.minis.api.validation.groups.update;

import org.hibernate.validator.constraints.NotBlank;

@Data
@NotDuplicateUser
public class UserForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(groups = { update.class })
    private String id;

    @NotBlank
    private String username;

    @NotBlank(groups = { create.class })
    private String password = "";

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    private String localName;

    private String locale;

    private boolean isLocked = false;

    private boolean isEnabled = false;

    private boolean isExpired = false;

    private boolean isPasswordExpired = false;

}
