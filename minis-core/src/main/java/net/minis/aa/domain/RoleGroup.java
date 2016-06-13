package net.minis.aa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.minis.api.spring.data.BaseModel;

import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@Entity
@Table(name = "sys_role_group")
public class RoleGroup implements BaseModel {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Role role;

}
