package seedu.securenus.command;

import seedu.securenus.Backend;
import seedu.securenus.exceptions.ExceptionMain;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.messages.InquiryMessages;
import seedu.securenus.secrets.WifiPassword;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

import java.util.HashSet;

/**
 * Represents a command to add a WiFi Password to the password manager.
 * Inherits from the AddSecretCommand class.
 */
public class AddWifiPasswordCommand extends AddSecretCommand {
    public static final String KEYWORD = "o/WifiPassword";
    private String username;
    private String password;

    /**
     * Constructs an AddWifiPasswordCommand object and initializes it with the user's input and usedNames.
     * Prompts the user to input the username and password for the Wifi Password.
     *
     * @param input The user's input.
     * @param usedNames The set of names used in the password manager.
     * @throws IllegalFolderNameException If the folder name is illegal.
     * @throws IllegalSecretNameException If the secret name is illegal.
     * @throws RepeatedIdException If the ID of the secret is repeated.
     * @throws OperationCancelException If the user cancels the operation.
     */
    public AddWifiPasswordCommand(String input, HashSet<String> usedNames) throws IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException, OperationCancelException {
        super(input, usedNames, KEYWORD);
        username = inquire(InquiryMessages.USERNAME, "Username");
        password = inquire(InquiryMessages.PASSWORD, "Password");
    }

    /**
     * Constructs an AddWifiPasswordCommand object and initializes it with a WifiPassword object.
     * @param wifiPassword The WifiPassword object.
     */
    public AddWifiPasswordCommand(WifiPassword wifiPassword) {
        this.name = wifiPassword.getName();
        this.folderName = wifiPassword.getFolderName();
        this.username = wifiPassword.getUsername();
        this.password = wifiPassword.getPassword();
    }

    /**
     * Adds the WiFi Password to the password manager.
     *
     * @param secureNUSData The password manager.
     * @throws ExceptionMain If an exception occurs while adding the secret to the password manager.
     */
    @Override
    public void execute(SecretMaster secureNUSData) throws ExceptionMain {
        assert secureNUSData != null;
        WifiPassword wifiPassword = new WifiPassword(name, folderName, username, password);
        try {
            secureNUSData.addSecret(wifiPassword);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (IllegalSecretNameException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (IllegalFolderNameException e) {
            throw new ExceptionMain(e.getMessage());
        }
        Ui.inform("I have added a new Wifi Password:\n" +
                "name       = " + name + "\n" +
                "Folder     = " + folderName + "\n" +
                "Username   = " + username + "\n" +
                "Password   = " + HIDDEN_FIELD);

        Backend.updateStorage(secureNUSData.listSecrets());
    }
}
