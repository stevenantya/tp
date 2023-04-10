package seedu.securenus.ui;

import seedu.securenus.SecureNUSLogger;
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
import java.util.logging.Level;

/**
 * Parses user commands and returns the corresponding command object.
 */
public class Parser {
    /**
     * Parses the user input command and returns the corresponding Command object.
     *
     * @param command the user input command string
     * @param usedNames a HashSet containing all the used secret names
     * @param folders a HashSet containing all the folder names
     *
     * @return the corresponding Command object
     * @throws InvalidCommandException if the command is invalid
     * @throws InsufficientParamsException if there are insufficient parameters for the command
     * @throws IllegalFolderNameException if the folder name is invalid
     * @throws IllegalSecretNameException if the secret name is invalid
     * @throws OperationCancelException if the user cancels an operation
     * @throws RepeatedIdException if there is a repeated ID for the student ID command
     * @throws InvalidFieldException if the field to edit is invalid
     * @throws SecretNotFoundException if the secret is not found
     * @throws FolderNotFoundException if the folder is not found
     * @throws NullSecretException if the secret is null
     * @throws NullFolderException if the folder is null
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
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, invalid command, " + command);
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses the "add" command and returns the corresponding Command object.
     *
     * @param command the "add" command to be parsed
     * @param usedNames a HashSet containing the names of all existing secrets to ensure uniqueness
     * @return the corresponding Command object
     * @throws InsufficientParamsException if the user input is insufficient
     * @throws IllegalFolderNameException if the folder name is illegal
     * @throws IllegalSecretNameException if the secret name is illegal
     * @throws OperationCancelException if the operation is cancelled
     * @throws RepeatedIdException if there is already a secret with the same ID
     * @throws InvalidFieldException if the field values are invalid
     */
    public static Command parseAdd(String command, HashSet<String> usedNames) throws InsufficientParamsException,
            IllegalFolderNameException, IllegalSecretNameException, OperationCancelException, RepeatedIdException,
            InvalidFieldException {
        if (command.startsWith("new o/")) {
            return parseAddSpecial(command, usedNames);
        } else if (command.startsWith("new ") && command.split(" ").length > 1) {
            return new AddBasicPasswordCommand(command, usedNames);
        } else {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, invalid command to add secret "
                + command);
            throw new InsufficientParamsException();
        }
    }

    /**
     * Parses the "add" command for special secret types and returns the corresponding Command object.
     *
     * @param command the "add" command to be parsed
     * @param usedNames a HashSet containing the names of all existing secrets to ensure uniqueness
     * @return the corresponding Command object
     * @throws OperationCancelException if the operation is cancelled
     * @throws IllegalFolderNameException if the folder name is illegal
     * @throws IllegalSecretNameException if the secret name is illegal
     * @throws RepeatedIdException if there is already a secret with the same ID
     * @throws InvalidFieldException if the field values are invalid
     * @throws InsufficientParamsException if the user input is insufficient
     */
    public static Command parseAddSpecial(String command, HashSet<String> usedNames) throws OperationCancelException,
            IllegalFolderNameException, IllegalSecretNameException, RepeatedIdException, InvalidFieldException,
            InsufficientParamsException {
        if (command.split(" ").length < 3) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, insufficient secret fields, " + command);
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
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, attempt to add invalid secret, " + command);
            throw new InvalidFieldException();
        }
    }

    /**
     * Checks if a given command has the correct format, starting with a specific keyword followed by the minimum
     * required
     * number of parameters.
     *
     * @param command the command to be checked
     * @param commandInitializer the specific keyword that the command must start with
     * @param minParams the minimum number of parameters required for the command
     * @throws InsufficientParamsException if the command has insufficient parameters
     * @throws InvalidCommandException if the command does not start with the specified keyword
     */
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
