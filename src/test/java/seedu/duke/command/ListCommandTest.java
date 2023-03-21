package seedu.duke.command;

import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class ListCommandTest {
    @Test
    void isExit_noFolder() {
        ListCommand listCommand = new ListCommand("list");
        assertFalse(listCommand.isExit());
    }

    @Test
    void isExit_withFolder() {
        ListCommand listCommand = new ListCommand("list f/Folder123!");
        assertFalse(listCommand.isExit());
    }

    @Test
    void getList_noFolder() {
        ListCommand listCommand = new ListCommand("list");
        assertEquals(listCommand.extractFolderName("list"), "unfiled");
    }

    @Test
    void getList_withFolder() {
        ListCommand listCommand = new ListCommand("list f/Folder123!");
        assertEquals(listCommand.extractFolderName("list f/Folder123!"), "f/Folder123!");
    }
}
