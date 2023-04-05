package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import seedu.duke.exceptions.RepeatedIdException;
// import seedu.duke.exceptions.secrets.IllegalFolderNameException;
// import seedu.duke.exceptions.secrets.IllegalSecretNameException;
// import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ViewCommandTest {
    private static final String TEST_NAME = "testName";
    private static final String TEST_USERNAME = "testUsername";
    private static final String TEST_PASSWORD = "testPassword";
    private static final String TEST_URL = "testURL.com";
    private static final String TEST_REVEAL_STR = String.format(
            "_____________________________________________________\n" +
            "Name: %s\n" +
            "Url: %s\n" +
            "Username: %s\n" +
            "Password: %s\n" +
            "_____________________________________________________",
            TEST_NAME, TEST_URL, TEST_USERNAME, TEST_PASSWORD);
    private static final String TEST_NAME_NOT_FOUND = "Oops! Error encountered: There are no passwords that matches " +
            "that name!\n" +
            "Make sure you follow this format: \"view PASSWORD_NAME\"";
    private final SecretMaster mockSecureNUSData = new SecretMaster();
    private final Secret mockBasicPassword;
    {
        mockBasicPassword = new BasicPassword(TEST_NAME, TEST_USERNAME,
                TEST_PASSWORD, TEST_URL);
    }
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    void extractName() throws SecretNotFoundException {
        HashSet<String> usedNames = new HashSet<String>();
        usedNames.add(TEST_NAME);
        ViewCommand viewCommand = new ViewCommand("view " + TEST_NAME, usedNames);
        assertEquals(TEST_NAME, viewCommand.extractName("view " + TEST_NAME));
    }

    @Test
    void inquirePassword() {
    }

    @Test
    void execute_matchingName() throws IllegalFolderNameException, RepeatedIdException, IllegalSecretNameException,
            FolderExistsException, SecretNotFoundException {
        mockSecureNUSData.addSecret(mockBasicPassword);
        ViewCommand viewCommand = new ViewCommand("view " + TEST_NAME, mockSecureNUSData.getSecretNames());
        viewCommand.execute(mockSecureNUSData);

        assertEquals(TEST_REVEAL_STR, output.toString().trim());
    }

    @Test
    public void execute_nonMatchingName() throws SecretNotFoundException {
        HashSet<String> usedNames = new HashSet<String>();
        usedNames.add(TEST_NAME);
        ViewCommand viewCommand = new ViewCommand("view " + TEST_NAME, usedNames);
        viewCommand.execute(mockSecureNUSData);

        assertEquals(TEST_NAME_NOT_FOUND, output.toString().trim());
    }

    @Test
    void isExit() throws SecretNotFoundException {
        HashSet<String> usedNames = new HashSet<String>();
        usedNames.add(TEST_NAME);
        ViewCommand viewCommand = new ViewCommand("view " + TEST_NAME, usedNames);
        assertFalse(viewCommand.isExit());
    }
}
