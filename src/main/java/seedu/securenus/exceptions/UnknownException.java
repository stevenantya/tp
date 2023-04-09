package seedu.securenus.exceptions;

/**
 * Exception thrown when an unknown exception occurs.
 */
public class UnknownException extends Exception {
    public UnknownException(String message) {
        super(message);
    }
}
