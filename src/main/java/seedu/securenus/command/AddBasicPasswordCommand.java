package seedu.securenus.command;

import seedu.securenus.exceptions.ExceptionMain;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.messages.InquiryMessages;
import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;
import seedu.securenus.Backend;

import java.util.HashSet;


/**
 * Represents a class to give a command to add a basic password.
 */
public class AddBasicPasswordCommand extends AddSecretCommand {

    public static final String KEYWORD = "new";
    private String username;
    private String password;
    private String url;


    /**
     * Constructs a command for adding a basic password entry to a password manager.
     *
     * @param input the user input string that triggered this command
     * @param usedNames a set of names that have already been used in the password manager
     * @throws IllegalSecretNameException if the name of the password entry is illegal
     * @throws IllegalFolderNameException if the name of the folder is illegal
     * @throws OperationCancelException if the user cancels the operation
     * @throws RepeatedIdException if the password manager already contains an entry with the same ID
     */
    public AddBasicPasswordCommand(String input, HashSet<String> usedNames) throws IllegalSecretNameException,
            IllegalFolderNameException, OperationCancelException, RepeatedIdException {
        super(input, usedNames, KEYWORD);
        this.url = inquire(InquiryMessages.URL, "URL");
        this.username = inquire(InquiryMessages.USERNAME, "Username");
        this.password = inquire(InquiryMessages.PASSWORD, "Password");
    }

    /**
     * Constructs a command for adding a basic password entry to a password manager based on an existing BasicPassword
     * object.
     *
     * @param basicPassword the BasicPassword object to use for constructing the command
     */
    public AddBasicPasswordCommand(BasicPassword basicPassword) {
        super(basicPassword);
        this.url = basicPassword.getUrl();
        this.username = basicPassword.getUsername();
        this.password = basicPassword.getPassword();
    }

    /**
     * Executes the AddBasicPasswordCommand.
     * Creates a new BasicPassword object with the provided name, folderName, username, password, and url.
     * Adds the BasicPassword object to the secret storage.
     * Prompts the user to input the username, password, and url if they are not provided in the input string.
     * If the BasicPassword object cannot be created or added to the storage, throws a RuntimeException.
     * Prints a success message upon completion of execution.
     *
     * @param secureNUSData The secret storage to add the BasicPassword object to.
     * @throws ExceptionMain if there is an issue adding the entry to the password manager
     */
    @Override
    public void execute(SecretMaster secureNUSData) throws ExceptionMain {
        assert secureNUSData != null;
        BasicPassword basicPasswordData = new BasicPassword(name,folderName,username,password,url);

        try {
            secureNUSData.addSecret(basicPasswordData);
        } catch (RepeatedIdException e) {
            throw new ExceptionMain("Repeated ID: Basic Password Command");
        } catch (FolderExistsException e) {
            throw new ExceptionMain("Unknown Error: Basic Password Command");
        } catch (IllegalSecretNameException e) {
            throw new ExceptionMain("Unknown Error: Basic Password Command");
        } catch (IllegalFolderNameException e) {
            throw new ExceptionMain("Unknown Error: Basic Password Command");
        }
        Backend.updateStorage(secureNUSData.listSecrets());
        Ui.inform("I have added a new basic password:\n" +
                "name     = " + name + "\n" +
               "folder   = " + folderName + "\n" +
               "url      = " + url + "\n" +
               "username = " + username + "\n" +
               "password = " + HIDDEN_FIELD);
    }

    /**
     * Returns false, indicating that this command does not result in program exit.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
