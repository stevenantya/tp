package seedu.securenus;


import seedu.securenus.secrets.Secret;
import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * JUnit test class for Backend.
 */
public class BackendTest {
    private static final Logger LOGGER = SecureNUSLogger.LOGGER;
    private static final String DATABASE_FILE = "testDatabase.txt";
    private static final String USER_DIRECTORY_IDENTIFIER = "user.dir";
    private static final String DATABASE_FOLDER = "assets";
    private static final String DELIMITER = ",";

    /**
     * Tests the reading of user data (all possible password fields) from the database
     */
    @Test
    public void readAndUpdateSecretsArrayListSecrets() {
        try {
            SecretMaster secretMaster = new SecretMaster();
            String[] basicPasswordArray = {"Password", "password1", "password1",
                "unnamed", "DKENCvtfsobnf2", "DKENCqbttxpse2", "gg.com"};
            String[] nusnetIdArray = {"nusNetId", "nusnet1", "nusnet1",
                "folder1", "e0987654", "DKENCqbttxpse"};
            String[] studentIdArray = {"studentID", "usr1", "usr1", "folder1", "A0987654X"};
            String[] creditCardArray = {"CreditCard", "usr2", "usr2", "folder1", "DKENCgvmmpnqf",
                "DKENC1111!1111!1111!1111", "DKENC234", "03/26",};
            String[] cryptoWalletArray = {"CryptoWallet", "usr3", "usr3", "folder1",
                "DKENCvtfsobnf2", "DKENCqbttxpse2", "DKENCqbttxpse2", "idk.com", "129"};
            String[] wifiPasswordArray = {"wifiPassword", "usr4", "usr4", "folder1",
                "DKENCvtfsobnf2", "DKENCqbttxpse2"};
            Backend.readAndUpdate(basicPasswordArray, secretMaster);
            Backend.readAndUpdate(nusnetIdArray, secretMaster);
            Backend.readAndUpdate(studentIdArray, secretMaster);
            Backend.readAndUpdate(creditCardArray, secretMaster);
            Backend.readAndUpdate(cryptoWalletArray, secretMaster);
            Backend.readAndUpdate(wifiPasswordArray, secretMaster);
            ArrayList<Secret> secretList = secretMaster.getSecretEnumerator().getList();
            assertEquals(secretList.get(0).toStringForDatabase(),
                    "Password,password1,password1,unnamed,DKENCvtfsobnf2,DKENCqbttxpse2,DKENCm");
            assertEquals(secretList.get(1).toStringForDatabase(),
                    "nusNetId,nusnet1,nusnet1,folder1,e0987654,DKENCqbttxpse");
            assertEquals(secretList.get(2).toStringForDatabase(),
                    "studentID,usr1,usr1,folder1,A0987654X");
            assertEquals(secretList.get(3).toStringForDatabase(),
                    "CreditCard,usr2,usr2,folder1,DKENCgvmmpnqf,DKENC1111!1111!1111!1111,DKENC234,03/26");
            assertEquals(secretList.get(4).toStringForDatabase(),
                    "CryptoWallet,usr3,usr3,folder1,DKENCvtfsobnf2,DKENCqbttxpse2,DKENCqbttxpse2");
            assertEquals(secretList.get(5).toStringForDatabase(),
                    "wifiPassword,usr4,usr4,folder1,DKENCvtfsobnf2,DKENCqbttxpse2");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Tests the encoding and decoding of special characters.
     */
    @Test
    public void encodeAndDecodeSpecialCharactersTrue() {
        assertEquals(
                Backend.decode(Backend.encode("~!@#$%^&*()_+{}|:<>?,./;'[]\\")), "~!@#$%^&*()_+{}|:<>?,./;'[]\\");
    }

    @Test
    public void encodeAndDecodeAlphanumericTrue() {
        assertEquals(
                Backend.decode(Backend.encode("1234567890QWERTYUIOPASDFGHJK" +
                        "LZXCVBNMqwertyuiopasdfghjklzxcvbnm")),
                "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm");
    }

    /**
     * Tests the parsing of empty fields.
     */
    @Test
    public void parseEmptyField_emptyEmpty() {
        assertEquals(Backend.parseEmptyField("empty"), "");
        assertNotEquals(Backend.parseEmptyField("Empty"), "");
    }

    @Test
    public void hashHashedDataTrue() {
        String[] basicPasswordArray = {"Password", "password1", "password1",
            "folder1", "DKENCvts2", "DKENCqbtt2", "idk.com", "5847"};
        SecretMaster secretMaster = new SecretMaster();
        try {
            secretMaster = Backend.readAndUpdate(basicPasswordArray, secretMaster);
            ArrayList<Secret> secretList = secretMaster.getSecretEnumerator().getList();
            String data = secretList.get(secretList.size() - 1).toStringForDatabase();
            boolean isCorrupted = !Backend.hash(data).equals(basicPasswordArray[basicPasswordArray.length - 1]);
            assertFalse(isCorrupted);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    @Test
    void updateStorage_updatesDatabaseFileWithNewSecret() {
        // Set up
        ArrayList<Secret> secrets = new ArrayList<>();
        Secret testBasicPassword = new BasicPassword("TestPassword", "testusername", "testpassword", "website.com");
        secrets.add(testBasicPassword);
        String pathOfCurrentDirectory = System.getProperty(USER_DIRECTORY_IDENTIFIER);
        String databasePath = pathOfCurrentDirectory + File.separator
                + DATABASE_FOLDER + File.separator + DATABASE_FILE;
        File database = new File(databasePath);
        if (database.exists()) {
            database.delete();
        }
        try {
            database.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Execution
        try {
            FileWriter myWriter = new FileWriter(database);
            for (int i = 0; i < secrets.size(); i++) {
                Secret secret = secrets.get(i);

                myWriter.write(secret.toStringForDatabase() + "," +
                        Backend.hash(secret.toStringForDatabase()) + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            Ui.inform("Database is not initialised! All user data will not be saved");
            LOGGER.log(Level.SEVERE, SecureNUSLogger.formatStackTrace(e.getStackTrace()));
        }

        // Assertion
        String expected = testBasicPassword.toStringForDatabase() + "," +
                Backend.hash(testBasicPassword.toStringForDatabase());
        String actual = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(database))) {
            actual = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }
}
