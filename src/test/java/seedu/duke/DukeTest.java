package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;

class DukeTest {
    private static final String DUKETEST_LOG_EXECUTECOMMANDEXITTRUE_IDENTIFIER
        = "DukeTest - executeCommandExitTrue: ";
    private static final String DUKETEST_LOG_EXECUTECOMMANDNOTEXITFALSE_IDENTIFIER
            = "DukeTest - executeCommand: ";

    @Test
    public void executeCommandExitTrue() {
        try {
            Duke duke = new Duke();
            Assertions.assertTrue(duke.executeCommand(Parser.parse("bye")));
            Assertions.assertTrue(duke.executeCommand(Parser.parse("hellooooo")));
        } catch (Exception e) {
            DukeLogger.setUpLogger();
            DukeLogger.LOGGER.log(Level.WARNING, DukeTest.DUKETEST_LOG_EXECUTECOMMANDEXITTRUE_IDENTIFIER, e);
            DukeLogger.close();
        }
    }

    @Test
    public void executeCommandNotExitFalse() {
        try {
            Duke duke = new Duke();
            Assertions.assertFalse(duke.executeCommand(Parser.parse("new o/NUSNet")));
            Assertions.assertFalse(duke.executeCommand(Parser.parse("new o/StudentID")));
            Assertions.assertFalse(duke.executeCommand(Parser.parse("new o/CryptoWallet")));
            Assertions.assertFalse(duke.executeCommand(Parser.parse("delete")));
            Assertions.assertFalse(duke.executeCommand(Parser.parse("list")));
            Assertions.assertFalse(duke.executeCommand(Parser.parse("search")));
            Assertions.assertFalse(duke.executeCommand(Parser.parse("view")));
            Assertions.assertFalse(duke.executeCommand(Parser.parse("edit")));
        } catch (Exception e) {
            DukeLogger.setUpLogger();
            DukeLogger.LOGGER.log(Level.WARNING,
                DukeTest.DUKETEST_LOG_EXECUTECOMMANDNOTEXITFALSE_IDENTIFIER, e);
            DukeLogger.close();
        }
    }
}
