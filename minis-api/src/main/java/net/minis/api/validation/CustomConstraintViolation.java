package net.minis.api.validation;

import java.util.Locale;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.minis.api.exception.NotSupportedException;
import net.minis.api.spring.ApplicationContextUtils;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@ToString(exclude = "leafBean")
@EqualsAndHashCode
public class CustomConstraintViolation<T> implements ConstraintViolation<T> {

    private T rootBean;

    private Object leafBean;

    private String propertyPath;

    private Object invalidValue;

    private String message;

    private String messageTemplate;

    public CustomConstraintViolation(T rootBean, Object leafBean, 
            String propertyPath, Object invalidValue, String message) {

        this(rootBean, leafBean, propertyPath, invalidValue, message, null);
    }

    public CustomConstraintViolation(T rootBean, Object leafBean, 
            String propertyPath, Object invalidValue, String message, String messageTemplate) {

        this.rootBean = rootBean;
        this.leafBean = leafBean;
        this.propertyPath = propertyPath;
        this.invalidValue = invalidValue;
        this.messageTemplate = messageTemplate;

        Locale locale = LocaleContextHolder.getLocale();
        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();
        MessageSource messageSource = applicationContext.getBean(MessageSource.class);
        this.message = messageSource.getMessage(messageTemplate, new Object[] {invalidValue}, message, locale);
    }

    @Override
    public T getRootBean() {
        return rootBean;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<T> getRootBeanClass() {
        return (Class<T>) rootBean.getClass();
    }

    @Override
    public Object getLeafBean() {
        return leafBean;
    }

    @Override
    public Path getPropertyPath() {
        return PathImpl.createPathFromString(propertyPath);
    }

    @Override
    public Object getInvalidValue() {
        return invalidValue;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getMessageTemplate() {
        return messageTemplate;
    }

    @Override
    public Object[] getExecutableParameters() {
        return null;
    }

    @Override
    public Object getExecutableReturnValue() {
        return null;
    }

    @Override
    public ConstraintDescriptor<?> getConstraintDescriptor() {
        return null;
    }

    @Override
    public <U> U unwrap(Class<U> type) {
        throw new NotSupportedException("The custom validation not supported for unwrapping.");
    }

}
