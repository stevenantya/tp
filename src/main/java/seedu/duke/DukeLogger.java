package seedu.duke;

import java.io.File;
import java.util.logging.*;

public class DukeLogger {
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String USER_DIRECTORY_IDENTIFIER = "user.dir";
    private static final String LOGGER_FOLDER = "assets";
    private static final String LOGGER_FILE = "logFiles.txt";

    public static void setUpLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        logger.addHandler(consoleHandler);

        try {
            String currentPath = System.getProperty(DukeLogger.USER_DIRECTORY_IDENTIFIER);
            String logFilesPath = java.nio.file.Paths.get(currentPath,
                    DukeLogger.LOGGER_FOLDER, DukeLogger.LOGGER_FILE).toString();
            File logFiles = new File(logFilesPath);
            if (!logFiles.createNewFile()) {
                logFiles.createNewFile();
            }
            FileHandler fileHandler = new FileHandler(logFilesPath, true);
            fileHandler.setLevel(Level.WARNING);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "DUKE logger is not working!");
        }

    }

    public static void close() {
        for (Handler handler : logger.getHandlers()) {
            handler.close();
        }
    }
}
