package seedu.duke.command;

import seedu.duke.exceptions.secrets.FolderNotFoundException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.NonExistentFolderException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a class to give a command to search for a secret by name in the SecretMaster.
 * Inherits from the Command class.
 */
public class SearchCommand extends Command{
    private final String name;
    private final String folderName;

    /**
     * Constructor for the SearchCommand.
     *
     * @param input The input String containing the name and optionally the folder name to search for.
     */
    public SearchCommand(String input,
             HashSet<String> folders) throws IllegalFolderNameException, IllegalSecretNameException,
             FolderNotFoundException {
        name = extractName(input, "search");
        folderName = extractFolderName(input);

        checkNameAndFolderName(name, folderName, folders);
    }

    public void checkNameAndFolderName(String name, String folderName,
            HashSet<String> folders) throws
            IllegalFolderNameException,
            IllegalSecretNameException, FolderNotFoundException {
        assert name != null;
        assert folders != null;
        if (folderName != null && !SecretMaster.isLegalFolderName(folderName)) {
            throw new IllegalFolderNameException();
        }
        if (Secret.isIllegalName(name)) {
            throw new IllegalSecretNameException();
        }
        if (folderName != null && !folders.contains(folderName)) {
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
        String extractedFolderName = null;
        if (input.split(" f/").length > 1) {
            extractedFolderName = input.split(" f/")[1];
            extractedFolderName = extractedFolderName.split(" ")[0];
        }
        return extractedFolderName;
    }

    /**
     * Searches for secrets in the SecretMaster with names that contain the specified name and optionally, in the
     * specified folder.
     * Prints out the search results in a formatted table.
     *
     * @param secureNUSData The SecretMaster containing the secrets to search in.
     */
    @Override
    public void execute(SecretMaster secureNUSData) throws NonExistentFolderException {
        assert secureNUSData != null;
        ArrayList<Secret> secrets;
        if (this.folderName != null) {
            // search a specific folder
            secrets = secureNUSData.listSecrets(this.folderName);
        } else {
            // search all passwords
            secrets = secureNUSData.listSecrets();
        }
        assert secrets != null;
        int count = 0;
        StringBuilder output = new StringBuilder();
        for (Secret secret : secrets) {
            if (secret.getName().contains(this.name)) { // case-sensitive search
                ++count;
                output.append(count).append("\t|\t").append(secret.getName()).append("\t|\t")
                        .append(secret.getFolderName()).append("\t|\t").append(secret.getType()).append("\t|\n");
            }
        }
        if (count == 0) {
            Ui.inform("No secrets found with the name provided.");
        } else {
            System.out.println("Found " + count + " matches!");
            System.out.print(output);
        }
    }

    /**
     * Returns a boolean indicating if this Command is an exit Command.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
