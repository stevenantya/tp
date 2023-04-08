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
<<<<<<< HEAD
=======
     * Tests the reading of user data (all possible password fields) from the database
     */
    @Test
    public void readAndUpdateSecretsArrayListSecrets() {
        try {
            SecretMaster secretMaster = Backend.initialiseSecretMaster();
            String[] basicPasswordArray = {"Password", "password1", "password1", "folder1",
                "DKENCvts3", "DKENCqbttxpse2", "idk.com", "6396"};
            String[] nusnetIdArray = {"nusNetId", "nusnet1", "nusnet1", "folder1", "e0123456",
                "DKENCrxfsuzvj", "4880"};
            String[] studentIdArray = {"studentID", "student2", "student2", "folder1", "A0123456R", "3931"};
            String[] creditCardArray = {"CreditCard", "credit1", "credit1", "personal", "usr1",
                "DKENC2345!6789!:123!4567", "DKENC234", "03/26", "5986"};
            String[] cryptoWalletArray = {"CryptoWallet", "wallet1", "wallet1", "personal", "DKENCvts2",
                "DKENCqsjwbuflfz", "DKENCtffeqisbtf", "idk.com","7516"};
            String[] wifiPasswordArray = {"wifiPassword", "wifi", "wifi", "personal", "DKENCipnf2",
                "DKENCqbttxpse3","5367"};
            secretMaster = Backend.readAndUpdate(basicPasswordArray, secretMaster);
            secretMaster = Backend.readAndUpdate(nusnetIdArray, secretMaster);
            secretMaster = Backend.readAndUpdate(studentIdArray, secretMaster);
            secretMaster = Backend.readAndUpdate(creditCardArray, secretMaster);
            secretMaster = Backend.readAndUpdate(cryptoWalletArray, secretMaster);
            secretMaster = Backend.readAndUpdate(wifiPasswordArray, secretMaster);
            Assertions.assertEquals(secretMaster.getByIndex(0).toStringForDatabase(),
                    "Password,password1,password1,folder1,DKENCvts3,DKENCqbttxpse2,idk.com");
            Assertions.assertEquals(secretMaster.getByIndex(1).toStringForDatabase(),
                    "nusNetId,nusnet1,nusnet1,folder1,e0123456,DKENCrxfsuzvj");
            Assertions.assertEquals(secretMaster.getByIndex(2).toStringForDatabase(),
                    "studentID,student2,student2,folder1,A0123456R");
            Assertions.assertEquals(secretMaster.getByIndex(3).toStringForDatabase(),
                    "CreditCard,credit1,credit1,personal,usr1,DKENC2345!6789!:123!4567,DKENC234,03/26");
            Assertions.assertEquals(secretMaster.getByIndex(4).toStringForDatabase(),
                    "CryptoWallet,wallet1,wallet1,personal,DKENCvts2,DKENCqsjwbuflfz,DKENCtffeqisbtf,idk.com,");
            Assertions.assertEquals(secretMaster.getByIndex(5).toStringForDatabase(),
                    "wifiPassword,wifi,wifi,personal,DKENCipnf2,DKENCqbttxpse3");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            throw new RuntimeException(e);
        }


    }

    /**
>>>>>>> f53cb3c9b583e854356c272b0b5df30f761e85ed
     * Tests the encoding and decoding of special characters.
     */
    @Test
    public void encodeAndDecodeSpecialCharactersTrue() {
        Assertions.assertEquals(
                Backend.decode(Backend.encode("~!@#$%^&*()_+{}|:<>?,./;'[]\\")), "~!@#$%^&*()_+{}|:<>?,./;'[]\\");
        //Assertions.assertEquals(Backend.decode("~!@#$%^&*()_+{}|:<>?,./;'[]\\"), "");
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
