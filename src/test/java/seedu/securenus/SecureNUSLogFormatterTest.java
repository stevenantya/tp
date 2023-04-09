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

        String expected = "\n[Level - INFO] \n@2022-01-01T00:00:00Z\n" +
                "Location: TestClass - testMethod\n[For exceptions] StackTrace: \nThis is a test message\n";
        String actual = formatter.format(record);

        assertEquals(expected, actual);
    }
}
