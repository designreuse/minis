package net.minis.aa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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
@Entity
@IdClass(ApplicationPermissionKey.class)
@Table(name = "sys_application_permission")
public class ApplicationPermission implements BaseModel {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "role", referencedColumnName = "id")
    private Role role;

    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "application", referencedColumnName = "id")
    private Application application;

}
