package net.minis.api.spring.webmvc.handler;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.minis.api.spring.webmvc.message.ValidationMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerMapping;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationMessages handleMethodArgumentNotValidException(
            HttpServletRequest request,
            MethodArgumentNotValidException exception) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        String path = (String) request
                .getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        ValidationMessages validationMessages = new ValidationMessages();
        validationMessages.setPath(path);
        validationMessages.setStatus(httpStatus.value());
        validationMessages.setStatusText(httpStatus.getReasonPhrase());
        validationMessages.setMessage("Validation Error.");

        BindingResult result = exception.getBindingResult();

        // Process global validations
        for (ObjectError globalError : result.getGlobalErrors()) {
            validationMessages.setMessage(globalError.getDefaultMessage());
        }

        // Process field validations
        for (FieldError fieldError : result.getFieldErrors()) {
            String field = fieldError.getField();
            Object invalidValue = fieldError.getRejectedValue();
            String errorMessage = this.getErrorMessage(fieldError);
            validationMessages.addFieldError(field, invalidValue, errorMessage);
        }

        return validationMessages;
    }

    private String getErrorMessage(FieldError fieldError) {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultMessage = fieldError.getDefaultMessage();
        String errorMessage = messageSource.getMessage(fieldError, locale);
        errorMessage = messageSource.getMessage(defaultMessage,
                fieldError.getArguments(), errorMessage, locale);
        return errorMessage;
    }

}
