package seedu.duke.command;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.InvalidURLException;
import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EditCommandTest {

    @Test
    void extract() {
        EditCommand editCommand = new EditCommand("edit p/PASSWORD_NAME -f nf/NEW_FOLDER_NAME " +
                "-d nd/NEW_DESCRIPTION -N np/NEW_PASSWORD_NAME");
        String[] expectedOutputs = {"PASSWORD_NAME", "NEW_FOLDER_NAME", "NEW_DESCRIPTION", "NEW_PASSWORD_NAME"};
        String[] actualOutputs = editCommand.extract("edit p/PASSWORD_NAME -f nf/NEW_FOLDER_NAME " +
                "-d nd/NEW_DESCRIPTION -N np/NEW_PASSWORD_NAME");
        assertArrayEquals(actualOutputs, expectedOutputs);
    }

    @Test
    public void execute_validName_nameUpdated() throws InvalidURLException, IllegalFolderNameException,
            RepeatedIdException, IllegalSecretNameException, FolderExistsException, SecretNotFoundException {
        SecretMaster mockSecureNUSData = new SecretMaster();
        Secret mockBasicPassword = new BasicPassword("name", "folder", "username", "password123", "url.com");
        mockSecureNUSData.addSecret(mockBasicPassword);
        EditCommand editCommand = new EditCommand("edit p/name -N np/newName");

        editCommand.execute(mockSecureNUSData);

        Secret updatedMockBasicPassword = mockSecureNUSData.getByName("newName");
        assertEquals("newName", updatedMockBasicPassword.getName());
    }

    @Test
    public void execute_invalidName_exceptionThrown() {
        SecretMaster mockSecureNUSData = new SecretMaster();
        EditCommand editCommand = new EditCommand("edit p/Password -N np/newPassword");

        assertThrows(RuntimeException.class, () -> editCommand.execute(mockSecureNUSData));
    }

    @Test
    void isExit() {
        EditCommand editCommand = new EditCommand("edit p/Test");
        assertFalse(editCommand.isExit());
    }
}
