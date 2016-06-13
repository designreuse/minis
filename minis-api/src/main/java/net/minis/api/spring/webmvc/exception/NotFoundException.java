package net.minis.api.spring.webmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs an <code>NotFoundException</code> with no detail message.
     */
    public NotFoundException() {
    }

    /**
     * Constructs an <code>NotFoundException</code> with the specified detail
     * message.
     *
     * @param message
     *            the detail message
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * <p>
     * Note that the detail message associated with <code>cause</code> is
     * <i>not</i> automatically incorporated in this exception's detail message.
     *
     * @param message
     *            the detail message (which is saved for later retrieval by the
     *            {@link Throwable#getMessage()} method).
     * @param cause
     *            the cause (which is saved for later retrieval by the
     *            {@link Throwable#getCause()} method). (A <tt>null</tt> value
     *            is permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
