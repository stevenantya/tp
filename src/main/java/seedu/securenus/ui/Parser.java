package seedu.securenus.ui;

import seedu.securenus.command.AddBasicPasswordCommand;
import seedu.securenus.command.AddCreditCardCommand;
import seedu.securenus.command.AddCryptoWalletCommand;
import seedu.securenus.command.AddNUSNetCommand;
import seedu.securenus.command.AddStudentIDCommand;
import seedu.securenus.command.AddWifiPasswordCommand;
import seedu.securenus.command.Command;
import seedu.securenus.command.DeleteCommand;
import seedu.securenus.command.EditCommand;
import seedu.securenus.command.ExitCommand;
import seedu.securenus.command.ListCommand;
import seedu.securenus.command.MenuCommand;
import seedu.securenus.command.SearchCommand;
import seedu.securenus.command.SaveCommand;
import seedu.securenus.command.ViewCommand;
import seedu.securenus.exceptions.InsufficientParamsException;
import seedu.securenus.exceptions.InvalidCommandException;
import seedu.securenus.exceptions.InvalidFieldException;
import seedu.securenus.exceptions.NullFolderException;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.FolderNotFoundException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.NullSecretException;
import seedu.securenus.exceptions.secrets.SecretNotFoundException;

import java.util.HashSet;

/**
 * Parses user commands and returns the corresponding command object.
 */
public class Parser {
    /**
     * Parses user input and returns the corresponding command object.
     *
     * @param command       user input command string
     * @return Command object corresponding to the user input
     */
    public static Command parse(String command, HashSet<String> usedNames, HashSet<String> folders) throws
            InvalidCommandException,
            InsufficientParamsException, IllegalFolderNameException, IllegalSecretNameException,
            OperationCancelException, RepeatedIdException, InvalidFieldException, SecretNotFoundException,
            FolderNotFoundException, NullSecretException, NullFolderException {
        if (command.startsWith("new")) {
            return parseAdd(command, usedNames);
        } else if (command.startsWith("delete")) {
            checkCommand(command, "delete", 2);
            return new DeleteCommand(command);
        } else if (command.equals("list") || command.startsWith("list ")) {
            return new ListCommand(command, folders);
        } else if (command.startsWith("search")) {
            checkCommand(command, "search", 2);
            return new SearchCommand(command, folders);
        } else if (command.startsWith("view")) {
            checkCommand(command, "view", 2);
            return new ViewCommand(command, usedNames);
        } else if (command.startsWith("edit")) {
            checkCommand(command, "edit", 2);
            return new EditCommand(command, usedNames);
        } else if (command.equals("menu")) {
            return new MenuCommand();
        } else if (command.equals("save")) {
            return new SaveCommand();
        }else if (command.equals("exit")) {
            return new ExitCommand();
        } else {
            // represents accidental wrong input
            throw new InvalidCommandException();
        }
    }

    public static Command parseAdd(String command, HashSet<String> usedNames) throws InsufficientParamsException,
            IllegalFolderNameException, IllegalSecretNameException, OperationCancelException, RepeatedIdException,
            InvalidFieldException {
        if (command.startsWith("new o/")) {
            return parseAddSpecial(command, usedNames);
        } else if (command.startsWith("new ") && command.split(" ").length > 1) {
            return new AddBasicPasswordCommand(command, usedNames);
        } else {
            throw new InsufficientParamsException();
        }
    }

    public static Command parseAddSpecial(String command, HashSet<String> usedNames) throws OperationCancelException,
            IllegalFolderNameException, IllegalSecretNameException, RepeatedIdException, InvalidFieldException,
            InsufficientParamsException {
        if (command.split(" ").length < 3) {
            throw new InsufficientParamsException();
        }
        if (command.startsWith("new " + AddCreditCardCommand.KEYWORD + " ")) {
            return new AddCreditCardCommand(command, usedNames);
        } else if (command.startsWith("new " + AddCryptoWalletCommand.KEYWORD + " ")) {
            return new AddCryptoWalletCommand(command, usedNames); // Have to change to AddCryptoWalletCommand
        } else if (command.startsWith("new " + AddNUSNetCommand.KEYWORD + " ")) {
            return new AddNUSNetCommand(command, usedNames);
        } else if (command.startsWith("new " + AddStudentIDCommand.KEYWORD + " ")) {
            return new AddStudentIDCommand(command, usedNames);
        } else if (command.startsWith("new " + AddWifiPasswordCommand.KEYWORD + " ")) {
            return new AddWifiPasswordCommand(command, usedNames); // Have to change to AddWifiPasswordCommand
        } else if (command.startsWith("new " + AddCreditCardCommand.KEYWORD + " ")) {
            return new AddCreditCardCommand(command, usedNames);
        } else {
            throw new InvalidFieldException();
        }
    }

    public static void checkCommand(String command, String commandInitializer, int minParams) throws
            InsufficientParamsException, InvalidCommandException {
        if (command.equals(commandInitializer)) {
            throw new InsufficientParamsException();
        }
        if (!command.startsWith(commandInitializer + " ")) {
            throw new InvalidCommandException();
        }
        if (command.split(" ").length < minParams) {
            throw new InsufficientParamsException();
        }
    }
}
