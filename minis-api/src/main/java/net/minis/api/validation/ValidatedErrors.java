package net.minis.api.validation;

import java.io.Serializable;
import java.util.Set;

import javax.validation.ConstraintViolation;

import lombok.Getter;

import org.apache.commons.beanutils.PropertyUtils;

import com.google.common.collect.Sets;

public class ValidatedErrors implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object validateObject;

    @Getter
    private Set<ConstraintViolation<Object>> constraintViolations = Sets.newLinkedHashSet();

    private ValidatedErrors(Object validateObject) {
        this.validateObject = validateObject;
        this.constraintViolations = Sets.newLinkedHashSet();
    }

    public void addErrorMessage(String propertyPath, String message) {
        addErrorMessage(propertyPath, message, message);
    }

    public void addErrorMessage(String propertyPath, String message, String messageTemplate) {

        try {

            Object invalidValue = PropertyUtils.getProperty(validateObject, propertyPath);

            CustomConstraintViolation<Object> violation = new CustomConstraintViolation<Object>(
                    validateObject, validateObject, propertyPath, invalidValue, message, messageTemplate);

            constraintViolations.add(violation);

        } catch (Exception e) {
            throw new RuntimeException("[Validation] Set the error message catch an exception: ", e);
        }

    }

    static ValidatedErrors createInstance(Object validateObject) {
        return new ValidatedErrors(validateObject);
    }

}
