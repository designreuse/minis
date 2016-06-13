package net.minis.api.validation;

import java.io.Serializable;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.collections.Predicate;

import com.google.common.collect.Sets;

/**
 * The validate error messages.
 * 
 * @author yen.
 */
@Getter
@EqualsAndHashCode(of = "name")
@ToString(of = { "name", "value", "errorMessages" })
public class ValidatedResult implements Serializable, Predicate {

    private static final long serialVersionUID = 1L;

    /**
     * the validate field name.
     */
    @Setter
    private String name;

    /**
     * the validate field value.
     */
    @Setter
    private Object value;

    /**
     * the validated error message.
     */
    private Set<String> errorMessages = Sets.newLinkedHashSet();

    /**
     * Construct validate error messages.
     * 
     * @param name
     *            - the validate field name.
     * @param value
     *            - the validate field value.
     */
    public ValidatedResult(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Add a validated error message.
     * 
     * @param errorMessage
     *            - the validated error messages.
     */
    public void addErrorMessage(String errorMessage) {
        errorMessages.add(errorMessage);
    }

    @Override
    public boolean evaluate(Object object) {
        if (name != null && name.equals(this.name)) {
            return true;
        }
        return false;
    }

}
