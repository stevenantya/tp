package seedu.securenus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.NullSecretException;
import seedu.securenus.exceptions.secrets.SecretNotFoundException;
import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretMaster;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * JUnit tests for the ViewCommand class.
 */
class ViewCommandTest {
    private static final String TEST_NAME = "testName";
    private static final String TEST_USERNAME = "testUsername";
    private static final String TEST_PASSWORD = "testPassword";
    private static final String TEST_URL = "testURL.com";
    private static final String TEST_REVEAL_STR = String.format("__________________________________" +
            "___________________\n" + "Name: %s\n" + "Url: %s\n" + "Username: %s\n" + "Password: %s\n" +
            "_____________________________________________________", TEST_NAME, TEST_URL, TEST_USERNAME, TEST_PASSWORD);
    private static final String TEST_NAME_NOT_FOUND = "_____________________________________________________" +
            "There are no passwords that matches that name!Make sure you follow this format: \"view PASSWORD_NAME\"" +
            "_____________________________________________________";
    private final SecretMaster mockSecureNUSData = new SecretMaster();

    /**
     * Mock data and streams for testing.
     */
    private final Secret mockBasicPassword; {
        mockBasicPassword = new BasicPassword(TEST_NAME, TEST_USERNAME, TEST_PASSWORD, TEST_URL);
    }

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * Set up streams for testing.
     */
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    /**
     * Tests the extraction of password name from user input.
     *
     * @throws SecretNotFoundException if the secret does not exist.
     * @throws NullSecretException if the secret is null.
     * @throws IllegalSecretNameException if the secret name is invalid.
     */
    @Test
    void extractName() throws SecretNotFoundException, NullSecretException, IllegalSecretNameException {
        HashSet<String> usedNames = new HashSet<String>();
        usedNames.add(TEST_NAME);
        ViewCommand viewCommand = new ViewCommand("view " + TEST_NAME, usedNames);
        assertEquals(TEST_NAME, viewCommand.extractName("view " + TEST_NAME));
    }

    /**
     * Tests the inquirePassword method.
     */
    @Test
    void inquirePassword() {
    }

    /**
     * Tests the execution of the command with matching secret name.
     *
     * @throws IllegalFolderNameException if folder name is invalid.
     * @throws RepeatedIdException if a secret with the same name or ID already exists.
     * @throws IllegalSecretNameException if secret name is invalid.
     * @throws FolderExistsException if folder already exists.
     * @throws NullSecretException if the secret is null.
     */
    @Test
    void execute_matchingName() throws IllegalFolderNameException, RepeatedIdException, IllegalSecretNameException,
            FolderExistsException, NullSecretException, SecretNotFoundException {
        mockSecureNUSData.addSecret(mockBasicPassword);
        ViewCommand viewCommand = new ViewCommand("view " + TEST_NAME, mockSecureNUSData.getSecretNames());
        viewCommand.execute(mockSecureNUSData);

        assertEquals(TEST_REVEAL_STR.replaceAll("(\\r|\\n)", ""), output.toString().replaceAll("(\\r|\\n)", ""));
    }

    /**
     * Tests the execution of the command with non-matching secret name.
     *
     * @throws NullSecretException if the secret is null.
     * @throws IllegalSecretNameException if secret name is invalid.
     */
    @Test
    public void execute_nonMatchingName() throws NullSecretException, IllegalSecretNameException,
            SecretNotFoundException {
        HashSet<String> usedNames = new HashSet<String>();
        usedNames.add(TEST_NAME);
        ViewCommand viewCommand = new ViewCommand("view " + TEST_NAME, usedNames);
        viewCommand.execute(mockSecureNUSData);

        assertEquals(TEST_NAME_NOT_FOUND.replaceAll("(\\r|\\n)", ""), output.toString().replaceAll("(\\r|\\n)", ""));
    }


    /**
     * This method tests if the isExit() method of the ViewCommand class works correctly
     * by checking that it does not return true when the command is "view" followed by a test name.
     *
     * @throws NullSecretException if the secret name is null
     * @throws IllegalSecretNameException if the secret name does not follow the required format
     */
    @Test
    void isExit() throws NullSecretException, IllegalSecretNameException, SecretNotFoundException {
        HashSet<String> usedNames = new HashSet<String>();
        usedNames.add(TEST_NAME);
        ViewCommand viewCommand = new ViewCommand("view " + TEST_NAME, usedNames);
        assertFalse(viewCommand.isExit());
    }
}
