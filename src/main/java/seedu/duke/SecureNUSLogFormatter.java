package seedu.duke;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SecureNUSLogFormatter extends Formatter{
    @Override
    public String format(LogRecord record) {
        String[] logArray = record.getMessage().split(",");
        if (logArray[0].equals("start")) {
            return SecureNUSLogFormatter.startOfSessionLog(record);
        } else if (logArray[0].equals("end")) {
            return SecureNUSLogFormatter.endOfSessionLog(record);
        }

        String logSequence = "\n[Level - " + record.getLevel() + "] \n";
        String logLevelAndDate = "@" + record.getInstant() + "\n";
        String logClassAndMethod = "Location: " + record.getSourceClassName() +
            " - " + record.getSourceMethodName() + "\n";
        String stackTraceIdentifier = "[For exceptions] StackTrace: \n";
        return logSequence + logLevelAndDate + logClassAndMethod + stackTraceIdentifier + record.getMessage() + "\n";
    }

    public static String startOfSessionLog(LogRecord record) {
        String alert = "-------------------------------------------------" +
                        "| Session began at: " + record.getInstant() + " |" +
                        "-------------------------------------------------";
        return alert;
    }

    public static String endOfSessionLog(LogRecord record) {
        String alert = "-------------------------------------------------" +
                "| Session ended at: " + record.getInstant() + " |" +
                "-------------------------------------------------";
        return alert;
    }
}
