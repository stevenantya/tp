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
public class SearchCommand extends Command {
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
        String tableHeader = String.format("|%1$5s|%2$25s|%3$19s|\n",
                centerString(5, "NO."),
                centerString(25, "NAME"),
                centerString(19, "FOLDER"));
        StringBuilder output = new StringBuilder();
        Ui.printLine();
        for (Secret secret : secrets) {
            if (secret.getName().contains(this.name)) { // case-sensitive search
                ++count;
                String name = secret.getName();
                String folder = secret.getFolderName();
                if (name.length() > 25) { // trim if exceeds table width
                    name = name.substring(0, 25);
                }
                if (folder.length() > 19) {
                    folder = folder.substring(0, 19);
                }
                output.append(String.format("|%1$5s|%2$25s|%3$19s|\n",
                        centerString(5, String.valueOf(count)),
                        centerString(25, name),
                        centerString(19, folder)));
            }
        }
        if (count == 0) {
            Ui.inform("No secrets found with the name provided.");
        } else {
            Ui.inform("Found " + count + " matches!\n" + tableHeader +
                    output.substring(0, output.length() - 1)); // substring is for removing last newline char
        }
    }

    public String centerString(int width, String s) {
        return String.format("%-" + width + "s",
                String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
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
