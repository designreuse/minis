package net.minis.api.web.kendo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Filter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String logic;
    
    private List<FilterField> filters;
    
}
