package seedu.duke.Command;

import seedu.duke.exceptions.NonExistentFolderException;
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
            return input.substring(input.indexOf("n/") + 2, input.indexOf("-f"));
        }
        return input.substring(input.indexOf("n/") + 2);
    }

    /**
     * return null if no "-f" in the input
     */
    public String extractFolderName(String input) {
        if (input.contains("-f")) {
            return input.substring(input.indexOf("f/") + 2);
        }
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
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
        for (Secret secret : secrets) {
            if (secret.getName().contains(this.name)) { // case-sensitive search
                continue;
                // TODO: list all passwords that match
            }
        }
    }
}
