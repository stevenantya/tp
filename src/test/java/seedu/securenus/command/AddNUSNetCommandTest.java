package seedu.securenus.command;

import org.junit.jupiter.api.Test;
import seedu.securenus.secrets.NUSNet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddNUSNetCommandTest {

    @Test
    void extractURL() {
        AddNUSNetCommand addNUSNet = new AddNUSNetCommand(new NUSNet("Name","FolderName","e0987654"));
        String input = " ";
        assertEquals("", addNUSNet.extractURL(input));
    }

    @Test
    void isExit() {
        AddNUSNetCommand addNUSNet = new AddNUSNetCommand(new NUSNet("Name","FolderName","e0987654"));
        assertFalse(addNUSNet.isExit());
    }
}
