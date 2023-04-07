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
     * Tests the reading of user data (all possible password fields) from the database
     */
    @Test
    public void readAndUpdateSecretsArrayListSecrets() {
        try {
            SecretMaster secretMaster = Backend.initialiseSecretMaster();
            String[] basicPasswordArray = {"Password", "password1", "password1",
                "unnamed", "DKENCvtfsobnf2", "DKENCqbttxpse2", "gg.com"};
            String[] nusnetIdArray = {"nusNetId", "nusnet1", "nusnet1",
                "folder1", "12345", "DKENCqbttxpse"};
            String[] studentIdArray = {"studentID", "usr1", "usr1", "folder1", "1234567"};
            String[] creditCardArray = {"CreditCard", "usr1", "usr1", "folder1", "fullname",
                "DKENCvtfsobnf2", "DKENC858", "03/26",};
            String[] cryptoWalletArray = {"CryptoWallet", "usr1", "usr1", "folder1",
                "DKENCvtfsobnf2", "DKENCqbttxpse2", "DKENCqbttxpse2", "idk.com", "129"};
            String[] wifiPasswordArray = {"wifiPassword", "usr1", "usr1", "folder1",
                "DKENCvtfsobnf2", "DKENCqbttxpse2"};
            secretMaster = Backend.readAndUpdate(basicPasswordArray, secretMaster);
            secretMaster = Backend.readAndUpdate(nusnetIdArray, secretMaster);
            secretMaster = Backend.readAndUpdate(studentIdArray, secretMaster);
            secretMaster = Backend.readAndUpdate(creditCardArray, secretMaster);
            secretMaster = Backend.readAndUpdate(cryptoWalletArray, secretMaster);
            secretMaster = Backend.readAndUpdate(wifiPasswordArray, secretMaster);
            Assertions.assertEquals(secretMaster.getByIndex(0).toStringForDatabase(),
                    "Password,password1,password1,unnamed,DKENCvtfsobnf2,DKENCqbttxpse2,gg.com");
            Assertions.assertEquals(secretMaster.getByIndex(1).toStringForDatabase(),
                    "nusNetId,nusnet1,nusnet1,folder1,12345,DKENCqbttxpse");
            Assertions.assertEquals(secretMaster.getByIndex(2).toStringForDatabase(),
                    "studentID,usr1,usr1,folder1,1234567");
            Assertions.assertEquals(secretMaster.getByIndex(3).toStringForDatabase(),
                    "CreditCard,usr1,usr1,folder1,fullname,DKENCvtfsobnf2,DKENC858,03/26");
            Assertions.assertEquals(secretMaster.getByIndex(4).toStringForDatabase(),
                    "CryptoWallet,usr1,usr1,folder1,DKENCvtfsobnf2,DKENCqbttxpse2,DKENCqbttxpse2,idk.com,");
            Assertions.assertEquals(secretMaster.getByIndex(5).toStringForDatabase(),
                    "wifiPassword,usr1,usr1,folder1,DKENCvtfsobnf2,DKENCqbttxpse2");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }


    }

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
            "folder1", "DKENCvts2", "DKENCqbtt2", "idk.com", "109"};
        SecretMaster secretMaster = Backend.initialiseSecretMaster();
        try {
            secretMaster = Backend.readAndUpdate(basicPasswordArray, secretMaster);
            String data = secretMaster.getByIndex(0).toStringForDatabase();
            boolean isCorrupted = !Backend.hash(data).equals(basicPasswordArray[basicPasswordArray.length - 1]);
            Assertions.assertFalse(isCorrupted);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

}
