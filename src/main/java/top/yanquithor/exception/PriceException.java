package top.yanquithor.exception;

/**
 * @author YanQuithor
 * @since 2023.12.22
 */
public class PriceException extends Exception {
    /**
     * Constructs a new price exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public PriceException() {
    }
    
    /**
     * Constructs a new price exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public PriceException(String message) {
        super(message);
    }
}
