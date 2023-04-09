package seedu.securenus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class SecureNUSLoggerTest {
    private static final String USER_DIRECTORY_IDENTIFIER = "user.dir";
    private static final String LOGGER_FOLDER = "assets";
    private static final  String LOGGER_FILE = "logFilesTest.txt";
    private Logger logger;
    private FileHandler fileHandler;

    @BeforeEach
    public void setUp() throws IOException {
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        String currentPath = System.getProperty(USER_DIRECTORY_IDENTIFIER);
        String logFilesPath = Paths.get(currentPath, LOGGER_FOLDER, LOGGER_FILE).toString();

        File logFiles = new File(logFilesPath);
        if (!logFiles.exists()) {
            logFiles.createNewFile();
        }

        fileHandler = new FileHandler(logFilesPath, true);
        fileHandler.setLevel(Level.WARNING);
        fileHandler.setFormatter(new SecureNUSLogFormatter());
        logger.addHandler(fileHandler);
    }

    @Test
    void setUpLogger() {
        assertNotNull(logger.getHandlers());
        assertEquals(1, logger.getHandlers().length);
        assertEquals(fileHandler, logger.getHandlers()[0]);
    }

    @Test
    void formatStackTrace() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String actualOutput = SecureNUSLogger.formatStackTrace(stackTrace);
        String expectedOutput = "";
        for (StackTraceElement stackTraceElement : stackTrace) {
            expectedOutput += ">>> " + stackTraceElement.toString() + "\n";
        }
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void close() {
        SecureNUSLogger.close();
        assertEquals(0, logger.getHandlers().length);
    }
}
