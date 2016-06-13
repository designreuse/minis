package net.minis.api.validation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class ValidationException extends RuntimeException {

    private ValidatedMessage validateMessage;

    public ValidationException(ValidatedMessage validatedMessage) {
        super(validatedMessage.toString());
        this.validateMessage = validatedMessage;
    }

}
