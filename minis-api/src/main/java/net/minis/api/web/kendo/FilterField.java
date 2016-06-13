package net.minis.api.web.kendo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FilterField extends Filter {

    private static final long serialVersionUID = 1L;

    private String field;

    private String value;

    private FilterOperator operator;

    private Boolean ignoreCase;
    
}
