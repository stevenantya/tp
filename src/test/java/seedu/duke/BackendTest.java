package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.duke.storage.SecretMaster;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JUnit test class for Backend.
 */
public class BackendTest {
    private static final Logger LOGGER  = SecureNUSLogger.LOGGER;

    /**
     * Tests the encoding and decoding of special characters.
     */
    @Test
    public void encodeAndDecodeSpecialCharactersTrue() {
        Assertions.assertEquals(
                Backend.decode(Backend.encode("~!@#$%^&*()_+{}|:<>?,./;'[]\\")), "~!@#$%^&*()_+{}|:<>?,./;'[]\\");
    }

    @Test
    public void encodeAndDecodeAlphanumericTrue() {
        Assertions.assertEquals(
                Backend.decode(Backend.encode("1234567890QWERTYUIOPASDFGHJK" +
                    "LZXCVBNMqwertyuiopasdfghjklzxcvbnm")),
                    "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm");
    }

    /**
     * Tests the parsing of empty fields.
     */
    @Test
    public void parseEmptyFieldEmptyEmpty() {
        Assertions.assertEquals(Backend.parseEmptyField("empty"), "");
        Assertions.assertNotEquals(Backend.parseEmptyField("Empty"), "");
    }

    @Test
    public void hashHashedDataTrue() {
        String[] basicPasswordArray = {"Password", "password1", "password1",
            "folder1", "DKENCvts2", "DKENCqbtt2", "idk.com", "5488"};
        SecretMaster secretMaster = Backend.initialiseSecretMaster();
        try {
            secretMaster = Backend.readAndUpdate(basicPasswordArray, secretMaster);
            String data = secretMaster.listSecrets().get(0).toStringForDatabase();
            boolean isCorrupted = !Backend.hash(data).equals(basicPasswordArray[basicPasswordArray.length - 1]);
            Assertions.assertFalse(isCorrupted);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

}
