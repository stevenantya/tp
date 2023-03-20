package seedu.duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditCommandTest {

    @Test
    void extract() {
        EditCommand editCommand = new EditCommand("edit p/PASSWORD_NAME -f nf/NEW_FOLDER_NAME -d nd/NEW_DESCRIPTION -N np/NEW_PASSWORD_NAME");
        String[] expectedOutputs = {"PASSWORD_NAME", "NEW_FOLDER_NAME", "NEW_DESCRIPTION", "NEW_PASSWORD_NAME"};
        String[] actualOutputs = editCommand.extract("edit p/PASSWORD_NAME -f nf/NEW_FOLDER_NAME -d nd/NEW_DESCRIPTION -N np/NEW_PASSWORD_NAME");
        assertArrayEquals(actualOutputs, expectedOutputs);
    }

//    @Test
//    void execute() {
//    }

    @Test
    void isExit() {
        EditCommand editCommand = new EditCommand("edit p/Test");
        assertFalse(editCommand.isExit());
    }
}