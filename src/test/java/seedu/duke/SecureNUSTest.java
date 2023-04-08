package seedu.duke;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.InsufficientParamsException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidFieldException;
import seedu.duke.exceptions.NullFolderException;
import seedu.duke.exceptions.OperationCancelException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.FolderNotFoundException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.NullSecretException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretEnumerator;
import seedu.duke.storage.SecretMaster;
import seedu.duke.storage.SecretSearcher;
import seedu.duke.ui.Parser;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecureNUSTest {
    private static final String DATABASE_FILE = "testDatabase.txt";
    private static final String USER_DIRECTORY_IDENTIFIER = "user.dir";
    private static final String DATABASE_FOLDER = "assets";
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
    public void executeCommand_exitCommand_successful() throws ExceptionMain, InsufficientParamsException,
            InvalidCommandException, IllegalSecretNameException, RepeatedIdException, InvalidFieldException,
            OperationCancelException, SecretNotFoundException, NullSecretException, FolderNotFoundException,
            IllegalFolderNameException, NullFolderException {
        String commandString = "exit";
        Command exitCommand = Parser.parse(commandString, secureNUSData.getSecretNames(), secureNUSData.getFolders());
        assertTrue(secureNUS.executeCommand(exitCommand));
    }

    @Test
    public void parseCommand_invalidCommand_exceptionThrown() throws IllegalFolderNameException,
            IllegalSecretNameException, SecretNotFoundException, FolderExistsException {
        String userInput = "invalid command";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        secureNUS.parseCommand();

        String expected = "Enter Command:_____________________________________________________\n" +
                "Invalid Command: Type 'menu' command to see the list of usable commands\n" +
                "_____________________________________________________\n";
        assertEquals(expected.replaceAll("(\\r|\\n)", ""),
                output.toString().replaceAll("(\\r|\\n)", ""));
    }
}
