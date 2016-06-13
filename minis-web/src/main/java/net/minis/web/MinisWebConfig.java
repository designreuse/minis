package net.minis.web;

import javax.annotation.PostConstruct;
import javax.validation.Validator;

import lombok.extern.slf4j.Slf4j;
import net.minis.api.spring.ApplicationContextUtils;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 有關 minis-web 模組的通用設定寫在這裡面.
 * 
 * @author yen
 */
@Slf4j
@Configuration
@ComponentScan("net.minis.aa")
public class MinisWebConfig {

    @PostConstruct
    public void init() {
        log.info("[Web] initialize minis web applications...");
    }

    @Bean
    public ApplicationContextUtils applicationContextUtils() {
        return new ApplicationContextUtils();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:validate-message", "classpath:message");
        return messageSource;
    }

    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource());
        return validatorFactoryBean;
    }

}
