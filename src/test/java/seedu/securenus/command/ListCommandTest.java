package seedu.securenus.command;

import org.junit.jupiter.api.BeforeEach;
import seedu.securenus.exceptions.NullFolderException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.FolderNotFoundException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.securenus.exceptions.secrets.InvalidExpiryDateException;
import seedu.securenus.exceptions.RepeatedIdException;


import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.secrets.CreditCard;
import seedu.securenus.secrets.CryptoWallet;
import seedu.securenus.secrets.NUSNet;
import seedu.securenus.secrets.StudentID;
import seedu.securenus.secrets.WifiPassword;

import seedu.securenus.storage.SecretMaster;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
/**
 * JUnit tests for the ListCommand class.
 */
public class ListCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Sets the output stream to the outputStreamCaptor before each test case is run.
     */
    @BeforeEach
    public void setStream() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Tests the isExit method when there is no folder name.
     */
    @Test
    void isExit_noFolder() throws FolderNotFoundException, IllegalFolderNameException, NullFolderException {
        HashSet<String> folders = new HashSet<>();
        folders.add("unnamed");
        ListCommand listCommand = new ListCommand("list", folders);
        assertFalse(listCommand.isExit());
    }

    /**
     * Tests the isExit method when there is a folder name.
     */
    @Test
    void isExit_withFolder() throws FolderNotFoundException, IllegalFolderNameException, NullFolderException {
        HashSet<String> folders = new HashSet<>();
        folders.add("Folder123");
        ListCommand listCommand = new ListCommand("list f/Folder123", folders);
        assertFalse(listCommand.isExit());
    }

    /**
     * Tests the extractFolderName method when there is no folder name.
     */
    @Test
    void getList_noFolder() throws FolderNotFoundException, IllegalFolderNameException, NullFolderException {
        HashSet<String> folders = new HashSet<>();
        folders.add("unnamed");
        ListCommand listCommand = new ListCommand("list", folders);
        assertEquals(listCommand.extractFolderName("list"), null);
    }

    /**
     * Tests the extractFolderName method when there is a folder name.
     */
    @Test
    void getList_withFolder() throws FolderNotFoundException, IllegalFolderNameException, NullFolderException {
        HashSet<String> folders = new HashSet<>();
        folders.add("Folder123");
        ListCommand listCommand = new ListCommand("list f/Folder123", folders);
        assertEquals(listCommand.extractFolderName("list f/Folder123"), "Folder123");
    }

    /**
     * Tests the maskStringPassword method.
     */
    @Test
    void testMaskStringPassword() throws FolderNotFoundException, IllegalFolderNameException, NullFolderException {
        HashSet<String> folders = new HashSet<>();
        folders.add("unnamed");
        ListCommand listCommand = new ListCommand("list", folders);
        String maskedPassword = listCommand.maskStringPassword("password");
        assertEquals("********", maskedPassword);
    }

    /**
     * Tests the maskIntPasswordAsString method.
     */
    @Test
    void testMaskIntPasswordAsString() throws FolderNotFoundException, IllegalFolderNameException, NullFolderException {
        HashSet<String> folders = new HashSet<>();
        folders.add("unnamed");
        ListCommand listCommand = new ListCommand("list", folders);
        String masked1234 = listCommand.maskIntPasswordAsString(1234);
        String masked5678 = listCommand.maskIntPasswordAsString(5678);

        assertEquals("****", masked1234);
        assertEquals("****", masked5678);
    }

    /**
     * Tests the ListCommand method for different secret types.
     */
    @Test
    void testSecretTypes() {
        SecretMaster secretMaster = new SecretMaster();

        try {
            secretMaster.addSecret(new BasicPassword("BasicPassword1", "Username1",
                    "Password1", "Url1.com"));
            secretMaster.addSecret(new CreditCard("CreditCard1", "John Doe",
                    "1234 5678 1234 5678", "123", "12/25"));
            secretMaster.addSecret(new CryptoWallet("CryptoWallet1", "Folder1",
                    "DeepsD", "PrivateKey1", "SeedPhrase1"));
            secretMaster.addSecret(new NUSNet("NUSNet1", "NUSNetId1",
                    "Password1"));
            secretMaster.addSecret(new StudentID("StudentID1",
                    "StudentID1"));
            secretMaster.addSecret(new WifiPassword("WifiPassword1", "Username1",
                    "Password1"));
            HashSet<String> folders = new HashSet<>();
            folders.add("unnamed");
            ListCommand listCommand = new ListCommand("list", folders);
            listCommand.execute(secretMaster);

        } catch (FolderExistsException | IllegalFolderNameException | IllegalSecretNameException | RepeatedIdException |
                 InvalidCreditCardNumberException | InvalidExpiryDateException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (FolderNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullFolderException e) {
            throw new RuntimeException(e);
        }
    }
}
