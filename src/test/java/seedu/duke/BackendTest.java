package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for Backend.
 */
public class BackendTest {

    /**
     * Tests the encoding and decoding of special characters.
     */
    @Test
    public void encodeAndDecodeSpecialCharactersTrue() {
        Assertions.assertEquals(
            Backend.decode(Backend.encode("~!@#$%^&*()_+{}|:<>?,./;'[]\\")), "~!@#$%^&*()_+{}|:<>?,./;'[]\\");
    }

    /**
     * Tests the parsing of empty fields.
     */
    @Test
    public void parseEmptyFieldEmptyEmpty() {
        Assertions.assertEquals(Backend.parseEmptyField("empty"), "");
        Assertions.assertNotEquals(Backend.parseEmptyField("Empty"), "");
    }

}
