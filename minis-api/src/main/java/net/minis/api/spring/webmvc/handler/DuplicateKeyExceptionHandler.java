package net.minis.api.spring.webmvc.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import net.minis.api.spring.webmvc.message.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerMapping;

@ControllerAdvice
public class DuplicateKeyExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateKeyException.class)
    public Message handleDuplicateKeyException(
            HttpServletRequest request,
            DuplicateKeyException exception) throws IOException {

        HttpStatus httpStatus = HttpStatus.CONFLICT;

        String path = (String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        Message message = new Message();
        message.setPath(path);
        message.setStatus(httpStatus.value());
        message.setStatusText(httpStatus.getReasonPhrase());
        message.setMessage(exception.getMessage());

        return message;
    }

}
