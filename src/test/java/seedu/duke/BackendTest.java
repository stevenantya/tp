package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.duke.secrets.Secret;


import java.util.ArrayList;
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
            ArrayList<Secret> secretList = new ArrayList<Secret>();
            String[] basicPasswordArray = {"Password", "password1", "password1",
                    "unnamed", "DKENCvtfsobnf2", "DKENCqbttxpse2", "gg.com"};
            String[] NUSNetIDArray = {"nusNetId", "nusnet1", "nusnet1",
                    "folder1", "12345", "DKENCqbttxpse"};
            String[] studentIDArray = {"studentID", "usr1", "usr1", "folder1", "1234567"};
            String[] creditCardArray = {"CreditCard", "usr1", "usr1", "folder1", "fullname",
                    "DKENCvtfsobnf2", "DKENC858", "03/26",};
            String[] cryptoWalletArray = {"CryptoWallet", "usr1", "usr1", "folder1",
                    "DKENCvtfsobnf2", "DKENCqbttxpse2", "DKENCqbttxpse2", "idk.com"};
            String[] wifiPasswordArray = {"wifiPassword", "usr1", "usr1", "folder1",
                    "DKENCvtfsobnf2", "DKENCqbttxpse2"};
            secretList = Backend.readAndUpdate(basicPasswordArray, secretList);
            secretList = Backend.readAndUpdate(NUSNetIDArray, secretList);
            secretList = Backend.readAndUpdate(studentIDArray, secretList);
            secretList = Backend.readAndUpdate(creditCardArray, secretList);
            secretList = Backend.readAndUpdate(cryptoWalletArray, secretList);
            secretList = Backend.readAndUpdate(wifiPasswordArray, secretList);
            Assertions.assertEquals(secretList.get(0).toStringForDatabase(),
                    "Password,password1,password1,unnamed,DKENCvtfsobnf2,DKENCqbttxpse2,gg.com");
            Assertions.assertEquals(secretList.get(1).toStringForDatabase(),
                    "nusNetId,nusnet1,nusnet1,folder1,12345,DKENCqbttxpse");
            Assertions.assertEquals(secretList.get(2).toStringForDatabase(),
                    "studentID,usr1,usr1,folder1,1234567");
            Assertions.assertEquals(secretList.get(3).toStringForDatabase(),
                    "CreditCard,usr1,usr1,folder1,fullname,DKENCvtfsobnf2,DKENC858,03/26");
            Assertions.assertEquals(secretList.get(4).toStringForDatabase(),
                    "CryptoWallet,usr1,usr1,folder1,DKENCvtfsobnf2,DKENCqbttxpse2,DKENCqbttxpse2,idk.com,");
            Assertions.assertEquals(secretList.get(5).toStringForDatabase(),
                    "wifiPassword,usr1,usr1,folder1,DKENCvtfsobnf2,DKENCqbttxpse2");
        } catch (Exception e) {

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



}
