package net.minis.aa.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.minis.aa.validation.validator.NotDuplicateUserValidator;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotDuplicateUserValidator.class)
@Documented
public @interface NotDuplicateUser {

    String message() default "NotDuplicateUser";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String id() default "id";

    String username() default "username";

}
