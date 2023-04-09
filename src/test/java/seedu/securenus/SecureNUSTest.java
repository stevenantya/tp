package seedu.securenus;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;

/**
 * JUnit test class for SecureNUS.
 */
class SecureNUSTest {
    private static final String DUKETEST_LOG_EXECUTECOMMANDEXITTRUE_IDENTIFIER
        = "DukeTest - executeCommandExitTrue: ";
    private static final String DUKETEST_LOG_EXECUTECOMMANDNOTEXITFALSE_IDENTIFIER
            = "DukeTest - executeCommand: ";

    /**
     * Logs a warning message if an exception is thrown.
     */
    @Test
    public void executeCommandExitTrue() {
        try {
            SecureNUS secureNUS = new SecureNUS();
        } catch (Exception e) {
            SecureNUSLogger.setUpLogger();
            SecureNUSLogger.LOGGER.log(Level.WARNING, SecureNUSTest.DUKETEST_LOG_EXECUTECOMMANDEXITTRUE_IDENTIFIER, e);
            SecureNUSLogger.close();
            throw new RuntimeException(e);
        }
    }
}
