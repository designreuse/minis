package net.minis.api.exception;

public class NotSupportedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new <code>NotSupportedException</code> without a detail
     * message.
     */
    public NotSupportedException() {
    }

    /**
     * Constructs an <code>NotSupportedException</code> with the specified
     * detail message.
     * 
     * @param msg
     *            the detail message.
     */
    public NotSupportedException(String msg) {
        super(msg);
    }

}
