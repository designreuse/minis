package net.minis.web;

import java.util.Locale;

import net.minis.api.spring.webmvc.WebAppPropertiesInterceptor;
import net.minis.api.spring.webmvc.handler.DuplicateKeyExceptionHandler;
import net.minis.api.spring.webmvc.handler.MethodArgumentNotValidExceptionHandler;
import net.minis.web.filter.MinisSiteMeshFilter;

import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * 有關 Web MVC 的設定均寫在這裡面設定.
 * 
 * @author yen
 */
@Configuration
public class MinisWebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 設定使用 SiteMesh 3 作為 Master Page 的 Template engine framework.
     * 
     * @return
     */
    @Bean
    public FilterRegistrationBean siteMeshFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MinisSiteMeshFilter());
        return filterRegistrationBean;
    }

    @Bean
    public DuplicateKeyExceptionHandler duplicateKeyExceptionHandler() {
        return new DuplicateKeyExceptionHandler();
    }

    @Bean
    public MethodArgumentNotValidExceptionHandler methodArgumentNotValidExceptionHandler() {
        return new MethodArgumentNotValidExceptionHandler();
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("zh", "TW"));
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Bean
    public WebAppPropertiesInterceptor webAppPropertiesInterceptor() {
        return new WebAppPropertiesInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(webAppPropertiesInterceptor());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setProviderClass(HibernateValidator.class);
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }

}
