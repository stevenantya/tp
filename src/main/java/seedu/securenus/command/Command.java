package seedu.securenus.command;

import seedu.securenus.SecureNUSLogger;
import seedu.securenus.exceptions.ExceptionMain;
import seedu.securenus.exceptions.NullFolderException;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.FolderNotFoundException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.NonExistentFolderException;
import seedu.securenus.exceptions.secrets.NullSecretException;
import seedu.securenus.exceptions.secrets.SecretNotFoundException;
import seedu.securenus.messages.InquiryMessages;
import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

import java.util.HashSet;
import java.util.logging.Level;

/**
 * The abstract class Command serves as a blueprint for all other command classes to inherit from. It contains two
 * abstract methods: isExit and execute, which must be implemented by any child classes.
 * This class also provides utility methods for child classes, such as inquire, extractName, nameCheck,
 * nameCheckWithExistence, folderCheckWithExistence, extractFolderName, query, and isEmptyEntry.
 */
public abstract class Command {
    protected static final String HIDDEN_FIELD = "*******";
    protected static final String CANCEL_COMMAND = "c/";
    private static final String EMPTY_STRING_REGEX = "[ ]*";
    private static final String FOLDER_DELIMITER = " f/";

    /**
     * Executes the command using the given SecretMaster object.
     *
     * @param secureNUSData the SecretMaster object to be used in executing the command
     * @throws SecretNotFoundException if the secret is not found
     * @throws ExceptionMain if there's any other exception
     * @throws NonExistentFolderException if the folder does not exist
     * @throws OperationCancelException if the operation is cancelled
     * @throws FolderExistsException if the folder already exists
     */

    public abstract void execute (SecretMaster secureNUSData) throws SecretNotFoundException, ExceptionMain,
            NonExistentFolderException, OperationCancelException, FolderExistsException;
    public boolean isExit() {
        return false;
    };

    /**
     * Inquires the user to answer a question.
     *
     * @param question the question to ask the user
     * @param fieldName the field name related to the question
     * @return the user's answer to the question
     * @throws OperationCancelException if the user cancels the operation
     */
    public String inquire(String question, String fieldName) throws OperationCancelException {
        assert question != null;
        assert fieldName != null;
        String result = query(question);
        while (isEmptyEntry(result)) {
            System.out.println(String.format(InquiryMessages.TEMPLATE_EMPTY, fieldName));
            result = query(question);
        }
        return result;
    }


    /**
     * Extracts the name of the secret from the input command.
     *
     * @param input the input command string
     * @param keyword the keyword to search for in the input command string
     * @return the name of the secret
     */
    public String extractName(String input, String keyword) {
        assert input != null;
        String extractedName = input.split(keyword + " ")[1];
        extractedName = extractedName.split(FOLDER_DELIMITER)[0];
        return extractedName;
    }

    /**
     * Checks the given name for validity and existence.
     *
     * @param name the name to check
     * @throws NullSecretException if the name is null or empty
     * @throws IllegalSecretNameException if the name is illegal
     */
    public void nameCheck(String name) throws NullSecretException, IllegalSecretNameException {
        if (name == "" || name == null) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, secret name is empty/null, " + name);
            throw new NullSecretException();
        }
        if (Secret.isIllegalName(name)) {
            throw new IllegalSecretNameException();
        }
    }

    /**
    * Checks the given name for validity, existence, and if it's in the usedNames HashSet.
    *
    * @param name the name to check
    * @param usedNames the HashSet of used names
    * @throws NullSecretException if the name is null or empty
    * @throws IllegalSecretNameException if the name is illegal
    * @throws SecretNotFoundException if the name is in the usedNames HashSet
    */
    public void nameCheckWithExistence(String name, HashSet<String> usedNames) throws NullSecretException,
            IllegalSecretNameException, SecretNotFoundException {
        if (name == "" || name == null) {
            throw new NullSecretException();
        }
        if (Secret.isIllegalName(name)) {
            throw new IllegalSecretNameException();
        }
        if (!usedNames.contains(name)) {
            throw new SecretNotFoundException();
        }
    }

    /**
     * Checks the given folder name for validity, existence, and if it's in the folders HashSet.
     *
     * @param folderName the folder name to check
     * @param folders the HashSet of existing folders
     * @throws NullFolderException if the folder name is null or empty
     * @throws FolderNotFoundException if the folder name is not in the folders HashSet
     * @throws IllegalFolderNameException if the folder name is illegal
     */
    public void folderCheckWithExistence(String folderName, HashSet<String> folders) throws NullFolderException,
            FolderNotFoundException, IllegalFolderNameException {
        if (folderName == "" || folderName == null) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, folder is empty/null, " + folderName);
            throw new NullFolderException();
        }
        if (!SecretMaster.isLegalFolderName(folderName)) {
            throw new IllegalFolderNameException();
        }
        if (!folders.contains(folderName)) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, non existent folder name, " + folderName);
            throw new FolderNotFoundException();
        }
    }

    /**
     * Extracts the folder name from the input command.
     *
     * @param input the input command string
     * @return the folder name
     */
    public String extractFolderName(String input) {
        assert input != null;
        String extractedFolderName = "unnamed";
        if (input.split(FOLDER_DELIMITER).length > 1) {
            extractedFolderName = input.split(FOLDER_DELIMITER)[1];
            extractedFolderName = extractedFolderName.split(" ")[0];
        }
        return extractedFolderName;
    }

    /**
     * Queries the user with a question and returns their response.
     *
     * @param question the question to ask the user
     * @return the user's response to the question
     * @throws OperationCancelException if the user cancels the operation
     */
    public String query(String question) throws OperationCancelException {
        assert question != null;
        System.out.println(question);
        String line = Ui.readLine();
        if (line.equals(CANCEL_COMMAND)) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "info,>>>>user cancelled operation");
            throw new OperationCancelException();
        }
        return line;
    }


    /**
     * Determines if the input string is empty or null.
     *
     * @param input the input string to check
     * @return true if the input string is empty or null, false otherwise
     */
    public boolean isEmptyEntry(String input) {
        return input.equals("") ||
                input.matches(EMPTY_STRING_REGEX) ||
                input == null;
    }

    /**
     * Prints the current state of the secureNUSData object.
     *
     * @param secureNUSData the SecretMaster object containing the current state
     */
    public void printCurrentState(SecretMaster secureNUSData) {
        // used for debugging
        String printout = "Current Folders [";
        for (String foldern: secureNUSData.getFolders()) {
            printout += "\n" + foldern + ',';
        }
        printout += "]";
        System.out.println(printout);
    }
}
