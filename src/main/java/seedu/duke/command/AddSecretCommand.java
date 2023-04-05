package seedu.duke.command;

import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

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
        Secret secret = new Secret(name,folderName);
        try {
            secureNUSData.addSecret(secret);
        } catch (RepeatedIdException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (FolderExistsException | IllegalSecretNameException | IllegalFolderNameException e) {
            throw new ExceptionMain(e.getMessage());
        }
        System.out.println("I have added a new Secret:\n");
        System.out.println("name     = " + name + "\n" +
                "folder   = " + folderName + "\n");
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false, as it is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
