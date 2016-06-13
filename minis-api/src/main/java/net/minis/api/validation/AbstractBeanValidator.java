package net.minis.api.validation;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

import javax.validation.ConstraintViolation;

import lombok.Getter;

@Getter
public abstract class AbstractBeanValidator<T> implements BeanValidator<T> {

    abstract protected void validate(T object, ValidatedErrors errors);

    /**
     * the class type of validate object.
     */
    private Class<T> validateClass;

    @SuppressWarnings("unchecked")
    public AbstractBeanValidator() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType ptype = (ParameterizedType) type;
        validateClass = (Class<T>) ptype.getActualTypeArguments()[0];
    }

    @Override
    public Set<ConstraintViolation<Object>> validate(T object) {
        ValidatedErrors errors = ValidatedErrors.createInstance(object);
        this.validate(object, errors);
        return errors.getConstraintViolations();
    }

}
