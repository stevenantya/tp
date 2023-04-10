package seedu.securenus.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import seedu.securenus.messages.OperationMessages;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @Test
    void greetUser() {
        Ui.greetUser();
        assertEquals(OperationMessages.INITIALISE.replaceAll("(\\r|\\n)", ""),
                output.toString().replaceAll("(\\r|\\n)", ""));
    }

    @Test
    public void printCorruptedDataMessage() {
        Ui.printCorruptedDataMessage();

        String expectedOutput = "    ____________\r\n" +
                "    | ALERT !! |\r\n" +
                "    ------------\r\n" +
                "Data stored is corrupted. Manual editing from the database.txt is not allowed!\r\n" +
                "1. Enter 'save' or continue this session to discard all previous data   OR\r\n" +
                "2. Press Ctrl + C to exit the application and manually revert the data in database.txt " +
                "back to it's last saved state\r\n" +
                "\r\n";

        assertEquals(expectedOutput.replaceAll("(\\r|\\n)", ""), output.toString().replaceAll("(\\r|\\n)", ""));
    }

    @Test
    public void printValidDataMessage() {
        Ui.printValidDataMessage();

        String expectedOutput = "---------------------------------------------------\r\n" +
                "| User data from previous session has been loaded |\r\n" +
                "---------------------------------------------------\r\n" +
                "\r\n";

        assertEquals(expectedOutput.replaceAll("(\\r|\\n)", ""), output.toString().replaceAll("(\\r|\\n)", ""));
    }

    @Test
    public void printNewSessionMessage() {
        Ui.printNewSessionMessage();

        String expectedOutput = "-----------------------------\r\n" +
                "| A new session has started |\r\n" +
                "-----------------------------\r\n" +
                "\r\n";

        assertEquals(expectedOutput.replaceAll("(\\r|\\n)", ""), output.toString().replaceAll("(\\r|\\n)", ""));
    }

    @Test
    public void printLine() {
        Ui.printLine();

        String expectedOutput = "_____________________________________________________\n";

        assertEquals(expectedOutput.replaceAll("(\\r|\\n)", ""), output.toString().replaceAll("(\\r|\\n)", ""));
    }

    @Test
    public void inform() {
        String input = "test message";
        Ui.inform(input);

        String expectedOutput = "_____________________________________________________\n"
                + input
                + "\n_____________________________________________________\n";

        assertEquals(expectedOutput.replaceAll("(\\r|\\n)", ""), output.toString().replaceAll("(\\r|\\n)", ""));
    }

    @Test
    public void informUserToStartCommand() {
        Ui.informUserToStartCommand();

        String expectedOutput = "Enter Command:";

        assertEquals(expectedOutput.replaceAll("(\\r|\\n)", ""), output.toString().replaceAll("(\\r|\\n)", ""));
    }

    @Test
    void removeBackSlashes() {
        String input = "C:\\Users\\John\\Documents\\file.txt";
        String expected = "C:\\\\Users\\\\John\\\\Documents\\\\file.txt";
        assertEquals(expected, Ui.removeBackSlashes(input));
    }

    @Test
    void removeExtraWhiteSpaces() {
        String input = "   test       ";
        String expected = "test";
        assertEquals(expected, Ui.removeExtraWhiteSpaces(input));
    }

    @Test
    void printError() {
        String input = "test";
        String expected = "Oops! Error encountered: test";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Ui.printError(input);
        assertEquals(expected.replaceAll("(\\r|\\n)", ""), output.toString().replaceAll("(\\r|\\n)", ""));
    }

    @Test
    void informOperationCancel() {
        Ui.informOperationCancel();
        assertEquals(OperationMessages.CANCEL_OPERATION.replaceAll("(\\r|\\n)", ""),
                output.toString().replaceAll("(\\r|\\n)", ""));
    }

    @Test
    @Order(7)
    void close() {
        assertDoesNotThrow(() -> Ui.close());
    }
}
