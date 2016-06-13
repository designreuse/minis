package net.minis.aa.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minis.api.spring.data.Audit;
import net.minis.api.spring.data.BaseModel;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@ToString(exclude = "password")
@EqualsAndHashCode(of = "username", callSuper = false)
@Entity
@Table(name = "sys_user")
public class User implements BaseModel {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(length = 200, nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(length = 200, nullable = false)
    private String password;

    @Column(length = 255)
    private String email;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 200)
    private String localName;

    @Column(length = 10)
    private String locale;

    @Column
    private boolean isLocked = false;

    @Column
    private boolean isEnabled = false;

    @Column
    private boolean isExpired = false;

    @Column
    private boolean isPasswordExpired = false;

    @Embedded
    private Audit audit;

}
