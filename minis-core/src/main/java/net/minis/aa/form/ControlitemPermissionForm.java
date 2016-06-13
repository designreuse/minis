package net.minis.aa.form;

import java.util.List;

import lombok.Data;

@Data
public class ControlitemPermissionForm {

    private String role;

    private List<String> controlitems;

}
