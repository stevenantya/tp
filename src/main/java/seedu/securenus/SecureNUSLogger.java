package seedu.securenus;

import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

public class SecureNUSLogger {
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String USER_DIRECTORY_IDENTIFIER = "user.dir";
    private static final String LOGGER_FOLDER = "assets";
    private static final String LOGGER_FILE = "logFiles.txt";

    //user input, separate test and runtime, clues on how to debug, create and
    public static void setUpLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        try {
            String currentPath = System.getProperty(SecureNUSLogger.USER_DIRECTORY_IDENTIFIER);
            String logFilesPath = Paths.get(currentPath,
                    SecureNUSLogger.LOGGER_FOLDER, SecureNUSLogger.LOGGER_FILE).toString();
            File logFiles = new File(logFilesPath);
            if (!logFiles.createNewFile()) {
                logFiles.createNewFile();
            }
            FileHandler fileHandler = new FileHandler(logFilesPath, true);
            fileHandler.setLevel(Level.WARNING);
            fileHandler.setFormatter(new SecureNUSLogFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "DUKE logger is not working!");
        }

    }

    public static String formatStackTrace(StackTraceElement[] stacktrace) {
        String formattedstacktrace = "";
        for (StackTraceElement stackTraceElement : stacktrace) {
            formattedstacktrace += ">>> " + stackTraceElement.toString() + "\n";
        }
        return formattedstacktrace;
    }

    public static void close() {
        for (Handler handler : LOGGER.getHandlers()) {
            handler.close();
        }
    }
}
