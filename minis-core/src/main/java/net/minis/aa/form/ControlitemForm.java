package net.minis.aa.form;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "id" })
public class ControlitemForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String description;

}
