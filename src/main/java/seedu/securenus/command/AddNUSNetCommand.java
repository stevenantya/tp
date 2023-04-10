package seedu.securenus.command;

import seedu.securenus.Backend;
import seedu.securenus.exceptions.ExceptionMain;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.messages.InquiryMessages;
import seedu.securenus.secrets.NUSNet;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

import java.util.HashSet;

/**
 * A command to add a NUSNet password entry to a password manager.
 */
public class AddNUSNetCommand extends AddSecretCommand {
    public static final String KEYWORD = "o/NUSNet";
    private String nusNetId;
    private String password;

    /**
     * Constructs a command for adding a NUSNet password entry to a password manager.
     *
     * @param input the user input string that triggered this command
     * @param usedNames a set of names that have already been used in the password manager
     * @throws IllegalFolderNameException if the name of the folder is illegal
     * @throws IllegalSecretNameException if the name of the password entry is illegal
     * @throws RepeatedIdException if the password manager already contains an entry with the same ID
     * @throws OperationCancelException if the user cancels the operation
     */
    public AddNUSNetCommand(String input, HashSet<String> usedNames) throws IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException, OperationCancelException {

        super(input, usedNames, KEYWORD);
        this.nusNetId = inquireNusNetId();
        this.password = inquire(InquiryMessages.PASSWORD, "Password");;
    }

    /**
     * Constructs a command for adding a NUSNet password entry to a password manager, using an existing NUSNet object.
     * @param nusNet the existing NUSNet object to use for constructing the command
     */
    public AddNUSNetCommand(NUSNet nusNet) {
        super(nusNet);
        this.nusNetId = nusNet.getNusNetId();
        this.password = nusNet.getPassword();
    }

    /**
     * Executes the command to add a NUSNet password entry to a password manager.
     *
     * @param secureNUSData the password manager to which the entry should be added
     * @throws ExceptionMain if there is an issue adding the entry to the password manager
     */
    @Override
    public void execute(SecretMaster secureNUSData) throws ExceptionMain {
        assert secureNUSData != null;
        NUSNet nusNetIdData = new NUSNet(name,folderName,nusNetId,password);
        try {
            secureNUSData.addSecret(nusNetIdData);
        } catch (RepeatedIdException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (FolderExistsException | IllegalSecretNameException | IllegalFolderNameException e) {
            throw new ExceptionMain(e.getMessage());
        }
        Ui.inform("I have added a new NUS Net ID password:\n" +
                "name       = " + name + "\n" +
                "folder     = " + folderName + "\n" +
                "NUS Net ID = " + nusNetId + "\n" +
                "password   = " + HIDDEN_FIELD);

        Backend.updateStorage(secureNUSData.listSecrets());
    }

    /**
     * Prompts the user for a NUSNet ID and ensures that the ID is legal.
     *
     * @return the legal NUSNet ID entered by the user
     * @throws OperationCancelException if the user cancels the operation
     */
    public String inquireNusNetId() throws OperationCancelException {
        String nusNetId = inquire(InquiryMessages.NUSNET_ID, "NUSNet ID");
        while (!NUSNet.isLegalId(nusNetId)) {
            nusNetId = inquire(InquiryMessages.NUSNET_ID_RETRY, "NUSNet ID");
        }
        return nusNetId;
    }

    /**
     * Extracts a URL from the user input string.
     *
     * @param input the user input string
     * @return an empty string, as NUSNet entries do not have a URL field
     */
    public String extractURL(String input) {
        return "";
    }

    /**
     * Indicates whether this command is an "exit" command.
     *
     * @return false, as this command is not an "exit" command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
