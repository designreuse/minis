package net.minis.aa.form.convert;

import javax.annotation.Resource;

import net.minis.aa.domain.User;
import net.minis.aa.form.UserForm;
import net.minis.aa.service.UserService;
import net.minis.api.convert.ModelConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserFormConverter extends ModelConverter<UserForm, User> {

    @Resource
    public UserService userService;

    public User convert(UserForm source, User target) {

        target.setUsername(source.getUsername());

        String password = source.getPassword();
        if (StringUtils.isNoneBlank(password)) {
            target.setPassword(password);
        }

        target.setEmail(source.getEmail());
        target.setName(source.getName());

        target.setLocalName(source.getLocalName());
        target.setLocale(source.getLocale());

        target.setEnabled(source.isEnabled());
        target.setLocked(source.isLocked());
        target.setExpired(source.isExpired());
        target.setPasswordExpired(source.isPasswordExpired());

        return target;
    }

}
