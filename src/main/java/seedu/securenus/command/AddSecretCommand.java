package seedu.securenus.command;

import seedu.securenus.SecureNUSLogger;
import seedu.securenus.exceptions.ExceptionMain;
import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

import java.util.HashSet;
import java.util.logging.Level;

/**
 * Represents the Class to give a command to add a secret.
 */
public abstract class AddSecretCommand extends Command {

    protected String name;
    protected String folderName;

    public AddSecretCommand() {}

    /**
     * Constructs a command for adding a secret entry to a password manager.
     *
     * @param input the user input string that triggered this command
     * @param usedNames a set of names that have already been used in the password manager
     * @param keyword the keyword that identifies the type of secret entry to add
     * @throws IllegalSecretNameException if the name of the secret entry is illegal
     * @throws IllegalFolderNameException if the name of the folder is illegal
     * @throws RepeatedIdException if the password manager already contains an entry with the same ID
     */
    public AddSecretCommand(String input, HashSet<String> usedNames, String keyword) throws IllegalSecretNameException,
            IllegalFolderNameException, RepeatedIdException {
        name = extractName(input, keyword);
        folderName = extractFolderName(input);
        checkNameAndFolderName(name, folderName, usedNames);
    }

    /**
     * Constructs a command for adding a secret entry to a password manager, using an existing secret object.
     *
     * @param secret the existing secret object to use for constructing the command
     */
    public AddSecretCommand(Secret secret) {
        name = secret.getName();
        folderName = secret.getFolderName();
    }

    /**
     * Checks that the name and folder name for a new secret entry are legal and not repeated.
     *
     * @param name the name of the new secret entry
     * @param folderName the folder name for the new secret entry
     * @param usedNames a set of names that have already been used in the password manager
     * @throws IllegalFolderNameException if the folder name is illegal
     * @throws IllegalSecretNameException if the name of the secret entry is illegal
     * @throws RepeatedIdException if the password manager already contains an entry with the same ID
     */
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
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, repeated secret name, " + name);
            throw new RepeatedIdException();
        }
    }

    /**
     * Executes the command to add a new secret entry to a password manager.
     *
     * @param secureNUSData the password manager to add the new secret entry to
     * @throws ExceptionMain if an error occurs during the execution of the command
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
