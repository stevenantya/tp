package seedu.securenus.command;

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

/**
 * The abstract class Command serves as a blueprint for all other command classes to inherit from. It contains two
 * abstract methods: isExit and execute, which must be implemented by any child classes.
 */
public abstract class Command {
    protected static final String HIDDEN_FIELD = "*******";
    protected static final String CANCEL_COMMAND = "c/";
    private static final String EMPTY_STRING_REGEX = "[ ]*";
    public abstract void execute (SecretMaster secureNUSData) throws SecretNotFoundException, ExceptionMain,
            NonExistentFolderException, OperationCancelException, FolderExistsException;
    public boolean isExit() {
        return false;
    };

    /**
     * Prompts the user for input and returns the user's response.
     *
     * @param question the question to ask the user
     * @return return the user's response
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
     * @return the name of the secret
     */
    public String extractName(String input, String keyword) {
        assert input != null;
        String extractedName = input.split(keyword + " ")[1];
        extractedName = extractedName.split(" f/")[0];
        return extractedName;
    }

    public void nameCheck(String name) throws NullSecretException, IllegalSecretNameException {
        if (name == "" || name == null) {
            throw new NullSecretException();
        }
        if (Secret.isIllegalName(name)) {
            throw new IllegalSecretNameException();
        }
    }


    public void nameCheckWithExistence(String name, HashSet<String> usedNames) throws NullSecretException,
            IllegalSecretNameException, SecretNotFoundException {
        if (name == "" || name == null) {
            throw new NullSecretException();
        }
        if (Secret.isIllegalName(name)) {
            throw new IllegalSecretNameException();
        }
        if (usedNames.contains(name)) {
            throw new SecretNotFoundException();
        }
    }

    public void folderCheckWithExistence(String folderName, HashSet<String> folders) throws NullFolderException,
            FolderNotFoundException, IllegalFolderNameException {
        if (folderName == "" || folderName == null) {
            throw new NullFolderException();
        }
        if (!SecretMaster.isLegalFolderName(folderName)) {
            throw new IllegalFolderNameException();
        }
        if (!folders.contains(folderName)) {

            throw new FolderNotFoundException();
        }
    }

    /**
     * Extracts the folder name of the secret from the input command.
     *
     * @param input the input command string
     * @return the folder name of the secret
     */
    public String extractFolderName(String input) {
        assert input != null;
        String extractedFolderName = "unnamed";
        if (input.split(" f/").length > 1) {
            extractedFolderName = input.split(" f/")[1];
            extractedFolderName = extractedFolderName.split(" ")[0];
        }
        return extractedFolderName;
    }

    public String query(String question) throws OperationCancelException {
        assert question != null;
        System.out.println(question);
        String line = Ui.readLine();
        if (line.equals(CANCEL_COMMAND)) {
            throw new OperationCancelException();
        }
        return line;
    }

    public boolean isEmptyEntry(String input) {
        return input.equals("") ||
                input.matches(EMPTY_STRING_REGEX) ||
                input == null;
    }

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
