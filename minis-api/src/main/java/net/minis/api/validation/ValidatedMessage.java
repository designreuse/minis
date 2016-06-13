package net.minis.api.validation;

import java.io.Serializable;
import java.util.Set;

import lombok.Getter;
import lombok.ToString;
import net.minis.api.lang.utils.SetsUtils2;

import com.google.common.collect.Sets;

@Getter
@ToString
public class ValidatedMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    public ValidatedMessage(String message) {
        this.message = message;
    }

    /**
     * The validated results.
     */
    private Set<ValidatedResult> validatedResults = Sets.newLinkedHashSet();

    /**
     * Add the validated results.
     * 
     * @param validatedProperty
     *            - the validated property.
     * @param validatedValue
     *            - the validated value.
     * @param errorMessage
     *            - the validated error message.
     */
    public void addValidatedResult(String validatedProperty, Object validatedValue, String errorMessage) {
        ValidatedResult validateResult = new ValidatedResult(validatedProperty, validatedValue);
        validateResult = SetsUtils2.findOrCreate(validatedResults, validateResult);
        validateResult.addErrorMessage(errorMessage);
        validatedResults.add(validateResult);
    }

}
