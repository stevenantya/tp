package seedu.securenus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

/**
 * JUnit test class for Backend.
 */
public class BackendTest {
    private static final Logger LOGGER  = SecureNUSLogger.LOGGER;

    /**
     * Tests the encoding and decoding of special characters using the Backend class.
     * Ensures that the original special character string can be encoded and decoded back to its original form.
    */
    @Test
    public void encodeAndDecodeSpecialCharactersTrue() {
        Assertions.assertEquals(
                Backend.decode(Backend.encode("~!@#$%^&*()_+{}|:<>?,./;'[]\\")), "~!@#$%^&*()_+{}|:<>?,./;'[]\\");
    }

    /**
     * Test if alphanumeric characters can be encoded and decoded correctly.
     */
    @Test
    public void encodeAndDecodeAlphanumericTrue() {
        Assertions.assertEquals(
                Backend.decode(Backend.encode("1234567890QWERTYUIOPASDFGHJK" +
                        "LZXCVBNMqwertyuiopasdfghjklzxcvbnm")),
                "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm");
    }

    /**
     * Test for parsing an "empty" field, which should return an empty string.
     */
    @Test
    public void parseEmptyFieldEmptyEmpty() {
        Assertions.assertEquals(Backend.parseEmptyField("empty"), "");
        Assertions.assertNotEquals(Backend.parseEmptyField("Empty"), "");
    }

}
