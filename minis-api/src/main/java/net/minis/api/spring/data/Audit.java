package net.minis.api.spring.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@Data
@JsonIgnoreType
public class Audit implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(length = 200)
    private String createBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @LastModifiedBy
    @Column(length = 200)
    private String modifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Version
    @Column
    private Integer version = 0;

}
