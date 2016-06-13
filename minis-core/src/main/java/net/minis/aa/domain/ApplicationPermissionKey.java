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
@EqualsAndHashCode(of = { "role", "application" })
public class ApplicationPermissionKey implements BaseModel {

    private static final long serialVersionUID = 1L;

    private String role;

    private String application;

    public ApplicationPermissionKey(String role, String application) {
        this.role = role;
        this.application = application;
    }

}
