package net.minis.api.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;

public interface BeanValidator<T> {

    /**
     * Get the class type of validate object.
     * 
     * @return the class type of validate object.
     */
    Class<T> getValidateClass();

    /**
     * Validate an object.
     * 
     * @param object
     *            - to validate object.
     * 
     * @return the validated constraint violation result.
     */
    Set<ConstraintViolation<Object>> validate(T object);

}
