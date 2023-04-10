package seedu.securenus.ui;

import org.junit.jupiter.api.Test;

import seedu.securenus.command.Command;
import seedu.securenus.command.EditCommand;
import seedu.securenus.command.ExitCommand;
import seedu.securenus.command.ListCommand;
import seedu.securenus.command.MenuCommand;
import seedu.securenus.command.SaveCommand;
import seedu.securenus.command.SearchCommand;
import seedu.securenus.command.ViewCommand;
import seedu.securenus.exceptions.InsufficientParamsException;
import seedu.securenus.exceptions.InvalidCommandException;
import seedu.securenus.exceptions.InvalidFieldException;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    public void parse_validInput_returnsCommand() {
        HashSet<String> usedNames = new HashSet<>();
        usedNames.add("password");
        HashSet<String> folders = new HashSet<>();
        Command command;

        try {
            command = Parser.parse("list", usedNames, folders);
            assertNotNull(command);
            assertTrue(command instanceof ListCommand);

            command = Parser.parse("search password", usedNames, folders);
            assertNotNull(command);
            assertTrue(command instanceof SearchCommand);

            command = Parser.parse("view password", usedNames, folders);
            assertNotNull(command);
            assertTrue(command instanceof ViewCommand);

            command = Parser.parse("edit password", usedNames, folders);
            assertNotNull(command);
            assertTrue(command instanceof EditCommand);

            command = Parser.parse("menu", usedNames, folders);
            assertNotNull(command);
            assertTrue(command instanceof MenuCommand);

            command = Parser.parse("save", usedNames, folders);
            assertNotNull(command);
            assertTrue(command instanceof SaveCommand);

            command = Parser.parse("exit", usedNames, folders);
            assertNotNull(command);
            assertTrue(command instanceof ExitCommand);
        } catch (Exception e) {
            fail("Exception thrown when not expected: " + e.getMessage());
        }
    }

    @Test
    public void parse_invalidInput_throwsException() {
        HashSet<String> usedNames = new HashSet<>();
        HashSet<String> folders = new HashSet<>();

        assertThrows(InvalidCommandException.class, () -> Parser.parse("invalid", usedNames, folders));
        assertThrows(InsufficientParamsException.class, () -> Parser.parse("new", usedNames, folders));
        assertThrows(InsufficientParamsException.class, () -> Parser.parse("delete", usedNames, folders));
        assertThrows(InsufficientParamsException.class, () -> Parser.parse("search", usedNames, folders));
        assertThrows(InsufficientParamsException.class, () -> Parser.parse("view", usedNames, folders));
        assertThrows(InsufficientParamsException.class, () -> Parser.parse("edit", usedNames, folders));
        assertThrows(InvalidCommandException.class, () -> Parser.parse("add", usedNames, folders));
    }

    @Test
    public void parseAdd_invalidCommand_throwInsufficientParamsException() {
        HashSet<String> usedNames = new HashSet<>();
        String command = "new";
        assertThrows(InsufficientParamsException.class, () -> Parser.parseAdd(command, usedNames));
    }

    @Test
    public void parseAdd_duplicateSecret_throwRepeatedIdException() throws IllegalFolderNameException,
            IllegalSecretNameException, InsufficientParamsException, OperationCancelException,
            InvalidFieldException, RepeatedIdException {
        HashSet<String> usedNames = new HashSet<>();
        usedNames.add("password1");
        String command = "new password1";
        assertThrows(RepeatedIdException.class, () -> Parser.parseAdd(command, usedNames));
    }

    @Test
    public void parseAddSpecial_insufficientParamsExceptionThrown() {
        HashSet<String> usedNames = new HashSet<>();
        String command = "new o/CreditCard";
        assertThrows(InsufficientParamsException.class, () -> Parser.parseAddSpecial(command, usedNames));
    }

    @Test
    public void parseAddSpecial_invalidFieldExceptionThrown() {
        HashSet<String> usedNames = new HashSet<>();
        String command = "new o/InvalidField name";
        assertThrows(InvalidFieldException.class, () -> Parser.parseAddSpecial(command, usedNames));
    }
    @Test
    public void checkCommand_validCommand() throws InvalidCommandException, InsufficientParamsException {
        String[] validCommands = {"delete password", "edit password", "search password", "view password"};
        String[] validCommandInitializers = {"delete", "edit", "search", "view"};
        for (int i = 0; i < validCommands.length; ++i) {
            int finalI = i;
            assertDoesNotThrow(() -> Parser.checkCommand(validCommands[finalI], validCommandInitializers[finalI], 2));
        }
    }

    @Test
    public void checkCommand_invalidCommand() {
        assertThrows(InsufficientParamsException.class, () -> Parser.checkCommand("edit", "edit", 1));
        assertThrows(InvalidCommandException.class, () -> Parser.checkCommand("invalid", "edit", 2));
        assertThrows(InsufficientParamsException.class, () -> Parser.checkCommand("edit password", "edit", 3));
    }
}
