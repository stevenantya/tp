package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BackendTest {

    @Test
    public void encodeAnddecode_specialCharacters_True() {
        Assertions.assertEquals(
            Backend.decode(Backend.encode("~!@#$%^&*()_+{}|:<>?,./;'[]\\")), "~!@#$%^&*()_+{}|:<>?,./;'[]\\");
    }

    @Test
    public void parseEmptyField_Empty_empty() {
        Assertions.assertEquals(Backend.parseEmptyField("empty"), "");
        Assertions.assertNotEquals(Backend.parseEmptyField("Empty"), "");
    }

}
