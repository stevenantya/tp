package seedu.securenus;

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
        } else if (logArray[0].equals("command")) {
            return SecureNUSLogFormatter.commandLog(logArray);
        } else if (logArray[0].equals("input")) {
            return SecureNUSLogFormatter.inputLog(logArray);
        } else if (logArray[0].equals("info")) {
            return SecureNUSLogFormatter.infoLog(logArray);
        } else if (logArray[0].equals("error")) {
            return SecureNUSLogFormatter.errorLog(record, logArray);
        } else if (logArray[0].equals("fatal")) {
            return SecureNUSLogFormatter.fatalLog(record, logArray);
        }
        return "";
    }

    public static String startOfSessionLog(LogRecord record) {
        String alert = "\n\n---------------------------------" +
                "| Session began at: " + record.getInstant() + " |" +
                "-----------------------------------";
        return alert;
    }

    public static String endOfSessionLog(LogRecord record) {
        String alert = "\n---------------------------------" +
                "| Session ended at: " + record.getInstant() + " |" +
                "----------------------------------";
        return alert;
    }

    public static String commandLog(String[] inputArray) {
        String header = "\n\ncommand by user: ";
        String parameters = "\nfields: ";
        return header + inputArray[1] + parameters;
    }

    public static String inputLog(String[] inputArray) {
        return inputArray[1] + ", ";
    }

    public static String infoLog(String[] inputArray) {
        return "\n" + inputArray[1];
    }

    public static String errorLog(LogRecord record, String[] inputArray) {
        String errorMessage = "\n>>>>Error: " + inputArray[1];
        String inputDetails = " | input: " + inputArray[2] + " | ";
        String methodDetails = " method & class: " + record.getSourceClassName() + " - " + record.getSourceMethodName();
        String continuedFields = "\nfields (continued): ";
        return errorMessage + inputDetails + methodDetails + continuedFields;
    }

    public static String fatalLog(LogRecord record, String[] inputArray) {
        String alert = "\n------------\n" +
                       "| ALERT !!!|\n" +
                       "------------\n";
        String header = "Program Crashed: ";
        String timeIdentifier = "\nTime: ";
        return alert + header + inputArray[1] +timeIdentifier + record.getInstant();
    }

}
