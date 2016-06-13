package net.minis.api.spring.webmvc.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "field")
public class FieldError implements Serializable {

    private static final long serialVersionUID = 1L;

    private String field;

    private Object invalidValue;

    private List<String> errorMessage = new ArrayList<>();

    public FieldError(String field, Object invalidValue) {
        this.field = field;
        this.invalidValue = invalidValue;
    }

    void addErrorMessage(String errorMessage) {
        this.errorMessage.add(errorMessage);
    }

}
