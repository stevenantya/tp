package seedu.securenus.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import seedu.securenus.messages.OperationMessages;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

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
    void readCommand() {
        String input = "new name";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        String actual = Ui.readCommand(scanner);
        assertEquals(input.replaceAll("(\\r|\\n)", ""), actual.toString().replaceAll("(\\r|\\n)", ""));
    }

    @Test
    void readLine() {
        String input = "test";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        String actual = Ui.readLine(scanner);
        assertEquals(input.replaceAll("(\\r|\\n)", ""), actual.toString().replaceAll("(\\r|\\n)", ""));
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
