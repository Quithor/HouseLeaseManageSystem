package top.yanquithor.exception;

/**
 * This is an exception class about user age
 * @author YanQuithor
 * @since 2023.12.21
 * */
public class AgeException extends Exception{
    /**
     * Constructs a new age exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public AgeException() {
    }
    
    /**
     * Constructs a new age exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public AgeException(String message) {
        super(message);
    }
}
