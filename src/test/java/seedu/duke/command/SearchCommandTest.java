package seedu.duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SearchCommandTest {

    @Test
    void extractName_nameOnly() {
        SearchCommand searchCommandNameOnly = new SearchCommand("search n/Name123!");
        assertEquals(searchCommandNameOnly.extractName("search n/Name123!"), "Name123!");
    }

    @Test
    void extractName_nameAndFolder() {
        SearchCommand searchCommand = new SearchCommand("search n/Name123! -f f/Folder123!");
        assertEquals(searchCommand.extractName("search n/Name123! -f f/Folder123!"), "Name123!");
    }
    @Test
    void extractFolderName_nameOnly() {
        SearchCommand searchCommand = new SearchCommand("search n/Name123!");
        assertNull(searchCommand.extractFolderName("search n/Name123!"));
    }

    @Test
    void extractFolderName_nameAndFolder() {
        SearchCommand searchCommand = new SearchCommand("search n/Name123! -f f/Folder123!");
        assertEquals(searchCommand.extractFolderName("search n/Name123! -f f/Folder123!"), "Folder123!");
    }

    // TODO: execute()

    @Test
    void isExit() {
        SearchCommand searchCommand = new SearchCommand("search n/Name123!");
        assertFalse(searchCommand.isExit());
    }
}
