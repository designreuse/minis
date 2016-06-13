package net.minis.api.validation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(METHOD)
@Retention(RUNTIME)
public @interface BeanValid {

    @SuppressWarnings("rawtypes")
    Class<? extends BeanValidator> value();

}
