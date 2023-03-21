package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BackendTest {

    @Test
    public void encodeAndDecodeSpecialCharactersTrue() {
        Assertions.assertEquals(
            Backend.decode(Backend.encode("~!@#$%^&*()_+{}|:<>?,./;'[]\\")), "~!@#$%^&*()_+{}|:<>?,./;'[]\\");
    }

    @Test
    public void parseEmptyFieldEmptyEmpty() {
        Assertions.assertEquals(Backend.parseEmptyField("empty"), "");
        Assertions.assertNotEquals(Backend.parseEmptyField("Empty"), "");
    }

}
