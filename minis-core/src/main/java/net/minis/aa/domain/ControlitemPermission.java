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
@EqualsAndHashCode(of = { "role", "controlitem" })
@Entity
@IdClass(ControlitemPermissionKey.class)
@Table(name = "sys_controlitem_permission")
public class ControlitemPermission implements BaseModel {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "role", referencedColumnName = "id")
    private Role role;

    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "controlitem", referencedColumnName = "id")
    private Controlitem controlitem;

}
