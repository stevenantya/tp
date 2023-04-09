package seedu.securenus.exceptions;

/**
 * ExceptionMain is a custom exception that extends the Exception class.
 * It is used for handling exceptions that occur during the main execution of the program.
 */
public class ExceptionMain extends Exception {

    /**
     * Constructs an ExceptionMain with the specified detail message.
     *
     * @param message the detail message.
     */
    public ExceptionMain(String message) {
        super(message);
    }
}
