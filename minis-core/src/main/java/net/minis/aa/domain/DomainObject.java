package net.minis.aa.domain;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DomainObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String source;

    private Map<String, Object> domain;

}
