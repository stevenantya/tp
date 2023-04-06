package seedu.duke;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SecureNUSLogFormatter extends Formatter{
    @Override
    public String format(LogRecord record) {
        String logSequence = "\n[Level - " + record.getLevel() + "] \n";
        String logLevelAndDate = "@" + record.getInstant() + "\n";
        String logClassAndMethod = "Location: " + record.getSourceClassName() + " - " + record.getSourceMethodName() + "\n";
        String stackTraceIdentifier = "[For exceptions] StackTrace: \n";
        return logSequence + logLevelAndDate + logClassAndMethod + stackTraceIdentifier + record.getMessage() + "\n";
    }
}
