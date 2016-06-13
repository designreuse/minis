package net.minis.aa.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.minis.aa.domain.User;
import net.minis.aa.service.UserService;
import net.minis.aa.validation.constraints.NotDuplicateUser;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class NotDuplicateUserValidator implements
ConstraintValidator<NotDuplicateUser, Object> {

    @Autowired
    private UserService userService;

    private String message;

    private String id;

    private String username;

    @Override
    public void initialize(NotDuplicateUser constraintAnnotation) {
        message = constraintAnnotation.message();
        id = constraintAnnotation.id();
        username = constraintAnnotation.username();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String validate_id = getId(value);
        String validate_username = getUsername(value);

        // if username is null, pass validation !!
        if (validate_username == null) {
            return true;
        }

        User user = userService.getUserByUsername(validate_username);

        // if user is not found, pass validation !!
        if (user == null) {
            return true;
        }

        // if user not null, check user is duplicate or not?
        String userId = user.getId();
        if (validate_id == null || StringUtils.equals(validate_id, userId) == false) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(this.username).addConstraintViolation();
            return false;
        }

        // pass all.
        return true;
    }

    private String getId(Object value) {
        try {
            return BeanUtils.getProperty(value, this.id);
        } catch (Exception e) {
            throw new IllegalStateException("Could not found property name"
                    + " [id] form bean object. id: " + this.id);
        }
    }

    private String getUsername(Object value) {
        try {
            return BeanUtils.getProperty(value, this.username);
        } catch (Exception e) {
            throw new IllegalStateException("Could not found property name"
                    + " [username] form bean object. username: " + this.username);
        }
    }

}
