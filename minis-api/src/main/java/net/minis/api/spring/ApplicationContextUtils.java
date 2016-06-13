package net.minis.api.spring;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext _applicationContext = null;

    public void setApplicationContext(ApplicationContext applicationContext) {
        _applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {

        if (_applicationContext == null) {
            throw new NoSuchBeanDefinitionException("May not be register 'ApplicationContextUtils' to spring bean.");
        }

        return _applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) {
        return _applicationContext.getBean(requiredType);
    }

}
