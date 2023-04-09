package seedu.securenus;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * SecureNUSLogFormatter is a custom log formatter that extends the
 * java.util.logging.Formatter class. It provides a specific log format
 * for the java.util.logging.Logger instances to follow.
 * The formatted log message includes the following information:
 * 1. Log level
 * 2. Timestamp of the log entry</li>
 * 3. Source class and method of the log entry</li>
 * 4. A stack trace identifier for exceptions (if any)</li>
 * 5. The actual log message</li>
 */
public class SecureNUSLogFormatter extends Formatter{

    /**
     * Formats the given LogRecord into a custom log message format.
     *
     * @param record The LogRecord to be formatted.
     * @return A formatted log message as a String.
     */
    @Override
    public String format(LogRecord record) {
        String logSequence = "\n[Level - " + record.getLevel() + "] \n";
        String logLevelAndDate = "@" + record.getInstant() + "\n";
        String logClassAndMethod = "Location: " + record.getSourceClassName() +
            " - " + record.getSourceMethodName() + "\n";
        String stackTraceIdentifier = "[For exceptions] StackTrace: \n";
        return logSequence + logLevelAndDate + logClassAndMethod + stackTraceIdentifier + record.getMessage() + "\n";
    }
}
