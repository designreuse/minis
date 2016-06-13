package net.minis.api.exception;

public class NotYetImplementedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new {@link NotYetImplementedException} without a detail
     * message.
     */
    public NotYetImplementedException() {
    }

    /**
     * Constructs an {@link NotYetImplementedException} with the specified
     * detail message.
     * 
     * @param message
     *            the detail message.
     */
    public NotYetImplementedException(String message) {
        super(message);
    }

}
