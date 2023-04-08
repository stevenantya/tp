package seedu.duke;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.secrets.InvalidExpiryDateException;
import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.Secret;
import seedu.duke.ui.Ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * JUnit test class for Backend.
 */
public class BackendTest {
    private static final Logger LOGGER  = SecureNUSLogger.LOGGER;
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
            ArrayList<Secret> secretList = new ArrayList<Secret>();
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
            secretList = Backend.readAndUpdate(basicPasswordArray, secretList);
            secretList = Backend.readAndUpdate(nusnetIdArray, secretList);
            secretList = Backend.readAndUpdate(studentIdArray, secretList);
            secretList = Backend.readAndUpdate(creditCardArray, secretList);
            secretList = Backend.readAndUpdate(cryptoWalletArray, secretList);
            secretList = Backend.readAndUpdate(wifiPasswordArray, secretList);
            assertEquals(secretList.get(0).toStringForDatabase(),
                    "Password,password1,password1,unnamed,DKENCvtfsobnf2,DKENCqbttxpse2,gg.com");
            assertEquals(secretList.get(1).toStringForDatabase(),
                    "nusNetId,nusnet1,nusnet1,folder1,12345,DKENCqbttxpse");
            assertEquals(secretList.get(2).toStringForDatabase(),
                    "studentID,usr1,usr1,folder1,1234567");
            assertEquals(secretList.get(3).toStringForDatabase(),
                    "CreditCard,usr1,usr1,folder1,fullname,DKENCvtfsobnf2,DKENC858,03/26");
            assertEquals(secretList.get(4).toStringForDatabase(),
                    "CryptoWallet,usr1,usr1,folder1,DKENCvtfsobnf2,DKENCqbttxpse2,DKENCqbttxpse2,idk.com,");
            assertEquals(secretList.get(5).toStringForDatabase(),
                    "wifiPassword,usr1,usr1,folder1,DKENCvtfsobnf2,DKENCqbttxpse2");
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
            "folder1", "DKENCvts2", "DKENCqbtt2", "idk.com", "109"};
        ArrayList<Secret> secretList = new ArrayList<Secret>();
        try {
            secretList = Backend.readAndUpdate(basicPasswordArray, secretList);
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
                        Backend.hash(secret.toStringForDatabase())+ "\n");
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

    @Test
    public void checkData_validData_noExceptionThrown() throws InvalidExpiryDateException {
        Secret testBasicPassword = new BasicPassword("TestPassword", "testusername", "testpassword", "website.com");
        String data = testBasicPassword.toStringForDatabase() + "," +
                Backend.hash(testBasicPassword.toStringForDatabase());
        ArrayList<Secret> secretList = new ArrayList<Secret>();
        String[] inputArray = data.split(DELIMITER);
        secretList = Backend.readAndUpdate(inputArray, secretList);
        String testData = secretList.get(0).toStringForDatabase();
        assertEquals(testData.equals(data), Backend.checkData(data));
    }
}
