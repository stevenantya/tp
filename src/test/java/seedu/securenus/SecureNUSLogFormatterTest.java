package seedu.securenus;

import java.time.Instant;
import java.util.logging.LogRecord;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SecureNUSLogFormatterTest {

    @Test
    public void format() {
        SecureNUSLogFormatter formatter = new SecureNUSLogFormatter();
        LogRecord record = new LogRecord(Level.INFO, "This is a test message");
        record.setInstant(Instant.parse("2022-01-01T00:00:00Z"));
        record.setSourceClassName("TestClass");
        record.setSourceMethodName("testMethod");

        String actual = formatter.format(record);

        assertEquals("", actual);
    }

    @Test
    void testStartOfSessionLog() {
        LogRecord record = new LogRecord(Level.INFO, "Session started");
        record.setInstant(Instant.now());
        String expected = "\n\n---------------------------------| Session began at: "
                + record.getInstant() + " |-----------------------------------";
        String actual = SecureNUSLogFormatter.startOfSessionLog(record);
        assertEquals(expected, actual);
    }

    @Test
    void testEndOfSessionLog() {
        LogRecord record = new LogRecord(Level.INFO, "Session ended");
        record.setInstant(Instant.now());
        String expected = "\n---------------------------------| Session ended at: "
                + record.getInstant() + " |----------------------------------";
        String actual = SecureNUSLogFormatter.endOfSessionLog(record);
        assertEquals(expected, actual);
    }

    @Test
    void testCommandLog() {
        String[] inputArray = {"", "commandName", "field1", "field2"};
        String expected = "\n\ncommand by user: commandName\nfields: ";
        String actual = SecureNUSLogFormatter.commandLog(inputArray);
        assertEquals(expected, actual);
    }

    @Test
    void testInputLog() {
        String[] inputArray = {"", "inputValue"};
        String expected = "inputValue, ";
        String actual = SecureNUSLogFormatter.inputLog(inputArray);
        assertEquals(expected, actual);
    }

    @Test
    void testInfoLog() {
        String[] inputArray = {"", "information message"};
        String expected = "\ninformation message";
        String actual = SecureNUSLogFormatter.infoLog(inputArray);
        assertEquals(expected, actual);
    }

    @Test
    void testErrorLog() {
        LogRecord record = new LogRecord(Level.SEVERE, "Error occurred");
        String[] inputArray = {"", "error message", "field1=value1, field2=value2"};
        record.setSourceClassName("TestClass");
        record.setSourceMethodName("testMethod");
        String expected = "\n>>>>Error: error message | input: field1=value1, field2=value2 |  " +
                "method & class: TestClass - testMethod\nfields (continued): ";
        String actual = SecureNUSLogFormatter.errorLog(record, inputArray);
        assertEquals(expected, actual);
    }

    @Test
    void testFatalLog() {
        LogRecord record = new LogRecord(Level.SEVERE, "Fatal error occurred");
        String[] inputArray = {"", "fatal error message"};
        record.setInstant(Instant.now());
        String expected = "\n------------\n| ALERT !!!|\n------------\n" +
                "Program Crashed: fatal error message\nTime: " + record.getInstant();
        String actual = SecureNUSLogFormatter.fatalLog(record, inputArray);
        assertEquals(expected, actual);
    }
}
