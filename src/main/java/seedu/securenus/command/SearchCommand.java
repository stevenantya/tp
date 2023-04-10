package seedu.securenus.command;

import seedu.securenus.SecureNUSLogger;
import seedu.securenus.exceptions.secrets.FolderNotFoundException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.NonExistentFolderException;
import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;

/**
 * Represents a class to give a command to search for a secret by name in the SecretMaster.
 * Inherits from the Command class.
 */
public class SearchCommand extends Command {
    private static final String FOLDER_DELIMITER = " f/";
    private String name;
    private String folderName;

    /**
     * Constructor for the search command.
     *
     * @param input the input string entered by the user
     * @param folders the set of existing folder names
     * @throws IllegalFolderNameException if the folder name is illegal
     * @throws IllegalSecretNameException if the secret name is illegal
     * @throws FolderNotFoundException if the folder is not found
     */
    public SearchCommand(String input,
                         HashSet<String> folders) throws IllegalFolderNameException, IllegalSecretNameException,
            FolderNotFoundException {
        name = extractName(input, "search");
        folderName = extractFolderName(input);

        checkNameAndFolderName(name, folderName, folders);
    }

    /**
     * Checks if the given name and folder name are legal and exist in the set of folders.
     *
     * @param name the name of the secret to be checked
     * @param folderName the name of the folder to be checked
     * @param folders the set of folder names to check against
     * @throws IllegalFolderNameException if the folder name is illegal
     * @throws IllegalSecretNameException if the secret name is illegal
     * @throws FolderNotFoundException if the folder does not exist in the set of folders
     */
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
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, folder name not found, " + folderName);
            throw new FolderNotFoundException();
        }
    }

    /**
     * Extracts the folder name from the input string if it exists.
     *
     * @param input the user input string
     * @return the extracted folder name or null if it does not exist in the input string
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
     * Executes a search for secrets in a specified folder or all folders with a given name.
     * Outputs a formatted table of the search results.
     *
     * @param secureNUSData the SecretMaster object containing the secrets to be searched
     * @throws NonExistentFolderException if a specified folder does not exist
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

    /**
     * Centers a string within a specified width.
     *
     * @param width the width to center the string within
     * @param s the string to center
     * @return the centered string
     */
    public String centerString(int width, String s) {
        return String.format("%-" + width + "s",
                String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
}
