package seedu.securenus;

import seedu.securenus.exceptions.NullFolderException;
import seedu.securenus.exceptions.secrets.FolderNotFoundException;
import seedu.securenus.exceptions.secrets.NonExistentFolderException;
import seedu.securenus.exceptions.secrets.NullSecretException;
import seedu.securenus.messages.OperationMessages;
import seedu.securenus.ui.Parser;
import seedu.securenus.ui.Ui;
import seedu.securenus.command.Command;
import seedu.securenus.exceptions.ExceptionMain;
import seedu.securenus.exceptions.InsufficientParamsException;
import seedu.securenus.exceptions.InvalidCommandException;
import seedu.securenus.exceptions.InvalidFieldException;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.SecretNotFoundException;
import seedu.securenus.messages.ErrorMessages;
import seedu.securenus.storage.SecretMaster;

import java.util.logging.Level;

/**
 * SecureNUS is the main class of the application.
 * It is responsible for initiating the application, running the application and handling user input.
 */
public class SecureNUS {
    private SecretMaster secureNUSData;

    /**
     * Constructor for SecureNUS class. It calls the initialisation method of Backend
     * to create a new SecretMaster object and assign it to the secureNUSData field.
     *
     * @throws FolderExistsException If a folder with the same name as the SecureNUS folder already exists
     * @throws IllegalFolderNameException If the SecureNUS folder name is illegal
     */
    public SecureNUS() throws FolderExistsException, IllegalFolderNameException {
        secureNUSData = Backend.initialisation();
        SecureNUSLogger.setUpLogger();
    }

    /**
     * The main method that starts the application.
     *
     * @param args Command line arguments
     * @throws FolderExistsException If a folder with the same name as the SecureNUS folder already exists
     * @throws IllegalFolderNameException If the SecureNUS folder name is illegal
     * @throws IllegalSecretNameException If the Secret name is illegal
     * @throws SecretNotFoundException If the Secret is not found
     */
    public static void main(String[] args) throws FolderExistsException, IllegalFolderNameException,
            IllegalSecretNameException, SecretNotFoundException {
        SecureNUS secureNUS = new SecureNUS();
        try {
            secureNUS.run();
        } catch (Exception e) {
            SecureNUSLogger.LOGGER.log(Level.SEVERE, "fatal, unexpected exception: " + e.getMessage());
            SecureNUSLogger.close();
        }
    }

    /**
     * The main method to run the application.
     */
    public void run() {
        Ui.greetUser();

        SecureNUSLogger.LOGGER.log(Level.INFO, "start,");
        if (Backend.isCorrupted) {
            Ui.printCorruptedDataMessage();
        } else if (!Backend.isDatabaseEmpty) {
            Ui.printValidDataMessage();
        } else {
            Ui.printNewSessionMessage();
        }
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
        Ui.inform(OperationMessages.SAVING);
        Backend.updateStorage(this.secureNUSData.listSecrets());
        Ui.inform(OperationMessages.SAVE_COMPLETE);
        Ui.inform(OperationMessages.CLOSE);
        Ui.close();
        SecureNUSLogger.LOGGER.log(Level.INFO, "end,");

    }

    /**
     * Parses the user input into a Command object.
     *
     * @return The parsed Command object.
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
     * Executes a given command on the SecureNUS application.
     *
     * @param command the command to be executed.
     * @return a boolean indicating whether the command has exited or not.
     * @throws ExceptionMain if an error occurs while executing the command.
     */
    public boolean executeCommand(Command command) throws ExceptionMain {
        if (command != null) {
            try {
                command.execute(secureNUSData);
                return command.isExit();
            } catch (NonExistentFolderException e) {
                Ui.printError("Folder Input does not exist");
            } catch (SecretNotFoundException e) {
                Ui.printError("Make sure you follow this format: \"edit p/PASSWORD_NAME\"");
            } catch (FolderExistsException e) {
                Ui.printError("Unknown Error: That folder already exists");
            } catch (OperationCancelException e) { // no issue, just cancel operation
                Ui.informOperationCancel();
            } catch (ExceptionMain e) {
                Ui.printError(e.getMessage());
            }
        }
        return false;
    }
}
