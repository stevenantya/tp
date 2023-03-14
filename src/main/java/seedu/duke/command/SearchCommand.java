package seedu.duke.command;

import seedu.duke.exceptions.secrets.NonExistentFolderException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

import java.util.ArrayList;

public class SearchCommand extends Command{
    private final String name;
    private final String folderName;
    public SearchCommand(String input) {
        this.name = extractName(input);
        this.folderName = extractFolderName(input);
    }

    public String extractName(String input) {
        if (input.contains("-f")) {
            return input.substring(input.indexOf("n/") + 2, input.indexOf("-f")).trim();
        }
        return input.substring(input.indexOf("n/") + 2).trim();
    }

    /**
     * return null if no "-f" in the input
     */
    public String extractFolderName(String input) {
        if (input.contains("-f")) {
            return input.substring(input.indexOf("f/") + 2).trim();
        }
        return null;
    }

    @Override
    public void execute(SecretMaster secureNUSData) {
        ArrayList<Secret> secrets;
        if (this.folderName != null) {
            // search a specific folder
            try {
                secrets = secureNUSData.listSecrets(this.folderName);
            } catch (NonExistentFolderException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            // search all passwords
            secrets = secureNUSData.listSecrets();
        }
        int count = 0;
        StringBuilder output = new StringBuilder();
        for (Secret secret : secrets) {
            if (secret.getName().contains(this.name)) { // case-sensitive search
                ++count;
                output.append(" ID: ").append(count).append("\t|\t").append(secret.getName()).append("\t");
            }
        }
        System.out.println("Found " + count + "match!");
        System.out.println(output.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
