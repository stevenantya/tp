package seedu.duke;

import seedu.duke.exceptions.NullFolderException;
import seedu.duke.exceptions.secrets.FolderNotFoundException;
import seedu.duke.exceptions.secrets.NonExistentFolderException;
import seedu.duke.exceptions.secrets.NullSecretException;
import seedu.duke.ui.Parser;
import seedu.duke.ui.Ui;
import seedu.duke.command.Command;
import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.InsufficientParamsException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidFieldException;
import seedu.duke.exceptions.OperationCancelException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.messages.ErrorMessages;
import seedu.duke.storage.SecretMaster;

import java.util.logging.Logger;
import java.util.logging.Level;

public class SecureNUS {
    private static final Logger LOGGER = SecureNUSLogger.LOGGER;
    private static final String DUKE_LOG_EXECUTECOMMAND_IDENTIFIER = "Duke - executeCommand";
    /**
     * Duke class handles the main entry-point for the application, parsing of user commands and execution of commands.
     * Duke class also initializes a SecretMaster object to store and manage the secrets for the application.
     */
    private SecretMaster secureNUSData;

    /**
     * Duke constructor initializes a SecretMaster object to store and manage the secrets for the application.
     *
     * @throws FolderExistsException         If the database folder already exists.
     * @throws IllegalFolderNameException    If the name of a folder is not valid.
     */
    public SecureNUS() throws FolderExistsException, IllegalFolderNameException {
        secureNUSData = Backend.initialisation();
        SecureNUSLogger.setUpLogger();
    }
    /**
     * Main entry-point for the Duke application.
     * Initializes a Duke object and runs the application.
     *
     * @param args An array of command-line arguments for the application.
     * @throws FolderExistsException         If the database folder already exists.
     * @throws IllegalFolderNameException    If the name of a folder is not valid.
     * @throws IllegalSecretNameException    If the name of a secret is not valid.
     * @throws SecretNotFoundException       If the specified secret cannot be found.
     */
    public static void main(String[] args) throws FolderExistsException, IllegalFolderNameException,
            IllegalSecretNameException, SecretNotFoundException {
        SecureNUS secureNUS = new SecureNUS();
        secureNUS.run();

    }

    /**
     * Starts the main loop of the Duke application.
     * Parses user input commands and executes them until the "exit" command is given.
     */
    public void run() {
        Ui.greetUser();

        boolean isExit = false;
        while (!isExit) {
            Command c = parseCommand();
            if (c == null) {
                continue;
            }
            try {
                isExit = executeCommand(c);
            } catch (ExceptionMain e) {
                Ui.inform("Unknown issue");
                return;
            }
            
        }
        Ui.close();
        Backend.updateStorage(this.secureNUSData.listSecrets());
    }


    /**
     * Reads user input command and returns a Command object.
     *
     * @return A Command object that represents the user input command.
     */
    public Command parseCommand() {
        Ui.informUserToStartCommand();
        String input = Ui.readCommand();
        Command command = null;
        try {
            command = Parser.parse(input, secureNUSData.getSecretNames(), secureNUSData.getFolders());
        } catch(InvalidCommandException e) {
            Ui.inform(ErrorMessages.INVALID_COMMAND);
            return null;
        } catch (InsufficientParamsException e) {
            Ui.inform(ErrorMessages.INSUFFICIENT_PARAMS);
        } catch (IllegalSecretNameException e) {
            Ui.inform(ErrorMessages.ILLEGAL_SECRET_NAME);
        } catch (IllegalFolderNameException e) {
            Ui.inform(ErrorMessages.ILLEGAL_FOLDER_NAME);
        } catch (OperationCancelException e) {
            Ui.informOperationCancel();
        } catch (RepeatedIdException e) {
            Ui.inform(ErrorMessages.REPEATED_ID);
        } catch (InvalidFieldException e) {
            Ui.inform(ErrorMessages.INVALID_FIELD);
        } catch (SecretNotFoundException e) {
            Ui.inform(ErrorMessages.SECRET_NOT_FOUND);
        } catch (FolderNotFoundException e) {
            Ui.inform(ErrorMessages.FOLDER_NOT_FOUND);
        } catch (NullSecretException e) {
            Ui.inform(ErrorMessages.NULL_SECRET);
        } catch (NullFolderException e) {
            Ui.inform(ErrorMessages.NULL_FOLDER);
        }
        return command;
    }

    /**
     * Executes the given Command object and returns a boolean indicating whether the application should exit.
     *
     * @param command The Command object to execute.
     * @return A boolean indicating whether the application should exit.
     * @throws IllegalFolderNameException    If the name of a folder is not valid.
     * @throws IllegalSecretNameException    If the name of a secret is not valid.
     * @throws SecretNotFoundException       If the specified secret cannot be found.
     */
    public boolean executeCommand(Command command) throws ExceptionMain {
        if (command != null) {
            try {
                command.execute(secureNUSData);
                return command.isExit();
            } catch (ExceptionMain e) {
                Ui.printError(e.getMessage());
                LOGGER.log(Level.SEVERE, DUKE_LOG_EXECUTECOMMAND_IDENTIFIER, e);
                SecureNUSLogger.close();
            } catch (NonExistentFolderException e) {
                Ui.printError("Folder Input does not exist");
            } catch (SecretNotFoundException e) {
                Ui.printError("Make sure you follow this format: \"edit p/PASSWORD_NAME\"");
            } catch (FolderExistsException e) {
                Ui.printError("Unknown Error: That folder already exists");
            } catch (OperationCancelException e) { // no issue, just cancel operation
                Ui.informOperationCancel();
            }
        }
        return false;
    }
}
