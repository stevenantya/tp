package seedu.securenus.command;

import seedu.securenus.exceptions.secrets.FolderNotFoundException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.NonExistentFolderException;
import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a class to give a command to search for a secret by name in the SecretMaster.
 * Inherits from the Command class.
 */
public class SearchCommand extends Command {
    private static final String FOLDER_DELIMITER = " f/";
    private String name;
    private String folderName;

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
        if (input.split(FOLDER_DELIMITER).length > 1) {
            extractedFolderName = input.split(FOLDER_DELIMITER)[1];
            extractedFolderName = extractedFolderName.split(FOLDER_DELIMITER)[0];
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
}
