package seedu.securenus;

import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

/**
 * SecureNUSLogger is a utility class that provides a configured logger for the
 * SecureNUS application. The logger writes log messages to a file with a custom
 * log format using the SecureNUSLogFormatter.
 * The logger can be used to log messages of different levels such as
 * INFO, WARNING, and SEVERE, among others.
 */

public class SecureNUSLogger {
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String USER_DIRECTORY_IDENTIFIER = "user.dir";
    private static final String LOGGER_FOLDER = "assets";
    private static final String LOGGER_FILE = "logFiles.txt";

    /**
     * Sets up the logger with configurations such as log level, file handler,
     * and custom log formatter. The method creates a log file in the specified
     * folder if it does not already exist.
     */
    public static void setUpLogger() {
        LogManager.getLogManager().reset();

        try {
            String currentPath = System.getProperty(SecureNUSLogger.USER_DIRECTORY_IDENTIFIER);
            String logFilesPath = Paths.get(currentPath,
                    SecureNUSLogger.LOGGER_FOLDER, SecureNUSLogger.LOGGER_FILE).toString();
            File logFiles = new File(logFilesPath);
            if (!logFiles.createNewFile()) {
                logFiles.createNewFile();
            }
            FileHandler fileHandler = new FileHandler(logFilesPath, true);
            fileHandler.setLevel(Level.INFO);
            fileHandler.setFormatter(new SecureNUSLogFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "fatal, logger is not functioning");
        }

    }

    /**
     * Formats the given stack trace elements array into a readable string
     * representation.
     *
     * @param stacktrace An array of StackTraceElement objects.
     * @return A formatted string representation of the stack trace.
     */
    public static String formatStackTrace(StackTraceElement[] stacktrace) {
        String formattedstacktrace = "";
        for (StackTraceElement stackTraceElement : stacktrace) {
            formattedstacktrace += ">>> " + stackTraceElement.toString() + "\n";
        }
        return formattedstacktrace;
    }

    /**
     * Closes all the handlers associated with the logger. This method should
     * be called when the application is shutting down or when the logger is
     * no longer needed.
     */
    public static void close() {
        for (Handler handler : LOGGER.getHandlers()) {
            handler.close();
            LOGGER.removeHandler(handler);
        }
    }
}
