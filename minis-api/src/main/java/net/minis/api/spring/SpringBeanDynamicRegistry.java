package net.minis.api.spring;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;

public class SpringBeanDynamicRegistry {

    public ApplicationContext applicationContext;

    public SpringBeanDynamicRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void registerBean(Class<?> beanType, String scope, boolean autowire) {

        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionRegistry beanRegistry = (BeanDefinitionRegistry) beanFactory;

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(beanType);
        beanDefinition.setScope(scope);

        if (autowire == true) {
            beanDefinition.setAutowireCandidate(true);
        }

        beanRegistry.registerBeanDefinition(beanType.getSimpleName(), beanDefinition);
    }

}