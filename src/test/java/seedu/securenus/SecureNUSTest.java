package seedu.securenus;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.securenus.command.Command;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretEnumerator;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.storage.SecretSearcher;
import seedu.securenus.ui.Parser;
import seedu.securenus.messages.ErrorMessages;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for SecureNUS.
 */
public class SecureNUSTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private SecureNUS secureNUS;
    private SecretMaster secureNUSData;

    @BeforeEach
    public void setUp() throws FolderExistsException, IllegalFolderNameException {
        secureNUS = new SecureNUS();

        Secret testBasicPassword = new BasicPassword("TestPassword", "testusername", "testpassword", "website.com");
        ArrayList<Secret> secrets = new ArrayList<>();
        secrets.add(testBasicPassword);
        Hashtable<String, ArrayList<Secret>> foldersHashTable = new Hashtable<>();
        foldersHashTable.put("Passwords", secrets);
        SecretEnumerator secretEnumerator = new SecretEnumerator(secrets, foldersHashTable);
        SecretSearcher secretSearcher = new SecretSearcher(new Hashtable<>(),
                Backend.createHashtableFolders(foldersHashTable));
        secureNUSData = new SecretMaster(secretSearcher, secretEnumerator);

        System.setOut(new PrintStream(output));
    }

    @AfterAll
    public static void reset() {
        System.setIn(System.in);
    }

    @Test
    public void executeCommand_exitCommand_successful() throws Exception {
        String commandString = "exit";
        Command exitCommand = Parser.parse(commandString, secureNUSData.getSecretNames(), secureNUSData.getFolders());
        assertTrue(secureNUS.executeCommand(exitCommand));
    }

    @Test
    public void parseCommand_invalidCommand_exceptionThrown() {
        String userInput = "invalid command";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        secureNUS.parseCommand();

        String expected = "Enter Command:_____________________________________________________\n" +
                ErrorMessages.INVALID_COMMAND +
                "_____________________________________________________\n";
        assertEquals(expected.replaceAll("(\\r|\\n)", ""),
                output.toString().replaceAll("(\\r|\\n)", ""));
    }
}
