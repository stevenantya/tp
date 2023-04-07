package seedu.duke.command;

import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.duke.exceptions.secrets.InvalidExpiryDateException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.CreditCard;
import seedu.duke.secrets.CryptoWallet;
import seedu.duke.secrets.NUSNet;
import seedu.duke.secrets.StudentID;
import seedu.duke.secrets.WifiPassword;
import seedu.duke.storage.SecretMaster;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * The EditCommandTest class tests the EditCommand class.
 */
class EditCommandTest{
    private static final String SECRET_NAME = "password";
    private static final String EDIT_COMMAND_INPUT = "edit password";
    private static final String CREDIT_CARD_NUMBER = "1234 5678 1234 5678";
    private static final String CVC_NUMBER = "123";
    private static final String EXPIRY_DATE = "12/22";
    private static final String NUSNET_ID = "e0123456";
    private static final String STUDENT_ID = "A1234567Z";
    private static final String USERNAME = "testuser";
    private static final String PASSWORD = "testpassword";
    private static final String URL = "testurl";
    private static final String FULL_NAME = "Test User";
    private static final String PRIVATE_KEY = "testprivatekey";
    private static final String SEED_PHRASE = "testseedphrase";
    private SecretMaster secretMaster;
    private HashSet<String> usedNames;
    private EditCommand editCommand;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    public void setStream() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @BeforeEach
    void setUp() {
        secretMaster = new SecretMaster();
        usedNames = new HashSet<>();
        usedNames.add(SECRET_NAME);
    }

    @Test
    void constructor_validInputName() {
        assertDoesNotThrow(() -> editCommand = new EditCommand(EDIT_COMMAND_INPUT, usedNames));
        assertEquals(SECRET_NAME, editCommand.extractName(EDIT_COMMAND_INPUT));
    }

    @Test
    void constructor_illegalInputName() {
        assertThrows(IllegalSecretNameException.class, () -> new EditCommand("edit /", usedNames));
    }

    @Test
    void constructor_nonExistentSecretName() {
        assertThrows(SecretNotFoundException.class, () -> new EditCommand("edit nonexistent", usedNames));
    }

    @Test
    void execute_basicPassword() throws SecretNotFoundException, FolderExistsException,
            IllegalFolderNameException, RepeatedIdException, IllegalSecretNameException {
        secretMaster.addSecret(new BasicPassword(SECRET_NAME, USERNAME, PASSWORD, URL));
        String[] newFields = {"NEW_USERNAME", "NEW_PASSWORD", "NEW_URL"};
        BasicPassword secret = (BasicPassword) secretMaster.getByName(SECRET_NAME);
        secretMaster.editSecret(secret, secret.getName(), secret.getFolderName(), newFields);
        String[] actualFields = {secret.getUsername(), secret.getPassword(), secret.getUrl()};
        assertArrayEquals(newFields, actualFields);
    }

    @Test
    void execute_creditCard() throws SecretNotFoundException, FolderExistsException, InvalidExpiryDateException,
            InvalidCreditCardNumberException, IllegalFolderNameException,
            RepeatedIdException, IllegalSecretNameException {
        secretMaster.addSecret(new CreditCard(SECRET_NAME, FULL_NAME, CREDIT_CARD_NUMBER, CVC_NUMBER, EXPIRY_DATE));
        String[] newFields = new String[]{"NEW_FULLNAME", "1111 2222 3333 4444", "999", "12/24"};
        CreditCard secret = (CreditCard) secretMaster.getByName(SECRET_NAME);
        secretMaster.editSecret(secret, secret.getName(), secret.getFolderName(), newFields);
        String[] actualFields = {secret.getFullName(), secret.getCreditCardNumber(),
                secret.getCvcNumber(), secret.getExpiryDate()};
        assertArrayEquals(newFields, actualFields);
    }

    @Test
    void execute_nusNet() throws SecretNotFoundException, FolderExistsException,
            IllegalFolderNameException, RepeatedIdException, IllegalSecretNameException {
        secretMaster.addSecret(new NUSNet(SECRET_NAME, NUSNET_ID, PASSWORD));
        String[] newFields = {"e0987364", "NEW_PASSWORD"};
        NUSNet secret = (NUSNet) secretMaster.getByName(SECRET_NAME);
        secretMaster.editSecret(secret, secret.getName(), secret.getFolderName(), newFields);
        String[] actualFields = {secret.getNusNetId(), secret.getPassword()};
        assertArrayEquals(newFields, actualFields);
    }

    @Test
    void execute_studentID() throws SecretNotFoundException, FolderExistsException,
            IllegalFolderNameException, RepeatedIdException, IllegalSecretNameException {
        secretMaster.addSecret(new StudentID(SECRET_NAME, STUDENT_ID));
        String[] newFields = {"A0987654X"};
        StudentID secret = (StudentID) secretMaster.getByName(SECRET_NAME);
        secretMaster.editSecret(secret, secret.getName(), secret.getFolderName(), newFields);
        String[] actualFields = {secret.getStudentID()};
        assertArrayEquals(newFields, actualFields);
    }

    @Test
    void execute_wifiPassword() throws SecretNotFoundException, FolderExistsException,
            IllegalFolderNameException, RepeatedIdException, IllegalSecretNameException {
        secretMaster.addSecret(new WifiPassword(SECRET_NAME, USERNAME, PASSWORD));
        String[] newFields = {"NEW_USERNAME", "NEW_PASSWORD"};
        WifiPassword secret = (WifiPassword) secretMaster.getByName(SECRET_NAME);
        secretMaster.editSecret(secret, secret.getName(), secret.getFolderName(), newFields);
        String[] actualFields = {secret.getUsername(), secret.getPassword()};
        assertArrayEquals(newFields, actualFields);
    }

    @Test
    void execute_cryptoWallet() throws SecretNotFoundException, FolderExistsException,
            IllegalFolderNameException, RepeatedIdException, IllegalSecretNameException {
        secretMaster.addSecret(new CryptoWallet(SECRET_NAME, USERNAME, PRIVATE_KEY, SEED_PHRASE));
        String[] newFields = {"NEW_USERNAME", "NEW_PRIVATE_KEY", "NEW_SEED_PHRASE"};
        CryptoWallet secret = (CryptoWallet) secretMaster.getByName(SECRET_NAME);
        secretMaster.editSecret(secret, secret.getName(), secret.getFolderName(), newFields);
        String[] actualFields = {secret.getUsername(), secret.getPrivateKey(), secret.getSeedPhrase()};
        assertArrayEquals(newFields, actualFields);
    }


    /**
     * Test the isExit method in EditCommand class.
     */
    @Test
    void isExit() throws IllegalSecretNameException, SecretNotFoundException {
        EditCommand editCommand = new EditCommand(EDIT_COMMAND_INPUT, usedNames);
        assertFalse(editCommand.isExit());
    }
}
