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
 * Represents the class to give a command to add a new Student ID to the SecureNUS system.
 */
public class AddWifiPasswordCommand extends AddSecretCommand {
    public static final String KEYWORD = "o/WifiPassword";
    private String username;
    private String password;

    /**
     * Constructs an AddStudentIDCommand object with the user input as the command parameter.
     *
     * @param input The command input entered by the user.
     */
    public AddWifiPasswordCommand(String input, HashSet<String> usedNames) throws IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException, OperationCancelException {
        super(input, usedNames, KEYWORD);
        username = inquire(InquiryMessages.USERNAME, "Username");
        password = inquire(InquiryMessages.PASSWORD, "Password");
    }
    public AddWifiPasswordCommand(WifiPassword wifiPassword) {
        this.name = wifiPassword.getName();
        this.folderName = wifiPassword.getFolderName();
        this.username = wifiPassword.getUsername();
        this.password = wifiPassword.getPassword();
    }

    /**
     * Executes the AddStudentIDCommand to add a new Student ID to the SecureNUS system.
     *
     * @param secureNUSData
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
