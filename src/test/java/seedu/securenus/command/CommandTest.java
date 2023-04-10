package seedu.securenus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.NullSecretException;
import seedu.securenus.exceptions.secrets.SecretNotFoundException;
import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.storage.SecretMaster;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    void nameCheckWithExistence() {
        Command command = (new AddBasicPasswordCommand(
                new BasicPassword("basicPassword1", "FolderName", "basicUsername", "Lorem Ipsum 112", "google.com")));
        HashSet<String> usedNames = new HashSet<>();
        String[] illegalNames = {"Illegal Name!*&@^*^%@$", null, "NonExistentName"};
        assertThrows(IllegalSecretNameException.class, () ->
                command.nameCheckWithExistence(illegalNames[0], usedNames));
        assertThrows(NullSecretException.class, () ->
                command.nameCheckWithExistence(illegalNames[1], usedNames));
        assertThrows(SecretNotFoundException.class, () ->
                command.nameCheckWithExistence(illegalNames[2], usedNames));
    }

    @Test
    void printCurrentState() throws IllegalFolderNameException, FolderExistsException {
        Command command = (new AddBasicPasswordCommand(
                new BasicPassword("basicPassword1", "FolderName", "basicUsername", "Lorem Ipsum 112", "google.com")));

        SecretMaster secureNUSData = new SecretMaster();
        secureNUSData.createFolder("FolderName");

        command.printCurrentState(secureNUSData);
        String expected = "Current Folders [\nFolderName,]\n".replaceAll("(\\r|\\n)", "");
        String actual = output.toString().replaceAll("(\\r|\\n)", "");
        assertEquals(expected, actual);
    }
}
