package top.yanquithor.exception;

/**
 * This is exception class about any id.
 * @author YanQuithor
 * @since 2023.12.21
 * */
public class IdException extends Exception {
    /**
     * Constructs a new id exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.<br>
     * Cause which Arise this exception maybe include id less than 0, id not enough bits.
     */
    public IdException() {
    }
    
    /**
     * Constructs a new id exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.<br>
     * Cause which Arise this exception maybe include id less than 0, id not enough bits.
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public IdException(String message) {
        super(message);
    }
}
