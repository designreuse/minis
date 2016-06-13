package net.minis.api.spring.webmvc.message;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minis.api.lang.utils.SetsUtils2;

@NoArgsConstructor
public class ValidationMessages extends Message {

    @Getter
    private Set<FieldError> fieldErrors = new HashSet<FieldError>();

    public void addFieldError(String field, Object invalidValue, String errorMessage) {
        FieldError fieldError = new FieldError(field, invalidValue);
        fieldError = SetsUtils2.findOrCreate(fieldErrors, fieldError);
        fieldError.addErrorMessage(errorMessage);
        fieldErrors.add(fieldError);
    }

}
