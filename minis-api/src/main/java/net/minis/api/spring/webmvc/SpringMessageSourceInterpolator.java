package net.minis.api.spring.webmvc;

import java.util.Locale;

import javax.validation.MessageInterpolator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

public class SpringMessageSourceInterpolator 
        implements MessageInterpolator, MessageSourceAware, InitializingBean {

    private MessageSource messageSource;

    @Override
    public String interpolate(String messageTemplate, Context context) {
        return messageSource.getMessage(messageTemplate, new Object[] {}, Locale.getDefault());
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        return messageSource.getMessage(messageTemplate, new Object[] {}, locale);
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (messageSource == null) {
            throw new IllegalStateException("MessageSource was not injected, could not initialize "
                    + this.getClass().getSimpleName());
        }
    }

}
