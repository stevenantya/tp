package seedu.securenus.command;

import seedu.securenus.exceptions.ExceptionMain;
import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

import java.util.HashSet;

/**
 * Represents the Class to give a command to add a secret.
 */
public abstract class AddSecretCommand extends Command {

    protected String name;
    protected String folderName;

    public AddSecretCommand() {}

    /**
     * Constructor for AddSecretCommand class.
     *
     * @param input the input command string
     */
    public AddSecretCommand(String input, HashSet<String> usedNames, String keyword) throws IllegalSecretNameException,
            IllegalFolderNameException, RepeatedIdException {
        name = extractName(input, keyword);
        folderName = extractFolderName(input);
        checkNameAndFolderName(name, folderName, usedNames);
    }


    public AddSecretCommand(Secret secret) {
        name = secret.getName();
        folderName = secret.getFolderName();
    }

    public void checkNameAndFolderName(String name, String folderName, HashSet<String> usedNames) throws
            IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException {
        assert name != null;
        assert folderName != null;
        assert usedNames != null;
        if (!SecretMaster.isLegalFolderName(folderName)) {
            throw new IllegalFolderNameException();
        }
        if (Secret.isIllegalName(name)) {
            throw new IllegalSecretNameException();
        }
        if (usedNames.contains(name)) {
            throw new RepeatedIdException();
        }
    }

    /**
     * Executes the AddSecretCommand.
     * Adds a new secret to the SecureNUS application.
     *
     * @param secureNUSData secureNUSData the secret master object
     */
    @Override
    public void execute(SecretMaster secureNUSData) throws ExceptionMain {
        assert secureNUSData != null;
        Secret secret = new Secret(name,folderName);
        try {
            secureNUSData.addSecret(secret);
        } catch (RepeatedIdException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (FolderExistsException | IllegalSecretNameException | IllegalFolderNameException e) {
            throw new ExceptionMain(e.getMessage());
        }
        Ui.inform("I have added a new Secret:\n" +
                "name     = " + name + "\n" +
                "folder   = " + folderName + "\n");


    }
}
