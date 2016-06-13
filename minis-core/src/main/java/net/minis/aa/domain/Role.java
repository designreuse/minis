package net.minis.aa.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minis.api.spring.data.Audit;
import net.minis.api.spring.data.BaseModel;

import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "sys_role")
public class Role implements BaseModel, GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 200)
    private String id;

    @Column(length = 200)
    private String name;

    @Column(length = 255)
    private String description;

    @Embedded
    private Audit audit;

    @Override
    public String getAuthority() {
        return Objects.toString(id);
    }

}
