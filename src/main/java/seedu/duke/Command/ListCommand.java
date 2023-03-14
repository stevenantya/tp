package seedu.duke.Command;

import seedu.duke.exceptions.NonExistentFolderException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

import java.util.ArrayList;

public class ListCommand extends Command {

    private final String folderName;

    public ListCommand(String input) {
        this.folderName = extractFolderName(input);
    }

    public String extractFolderName(String input) {
        String extractedFolderName = "unfiled";
        if (input.split(" ").length > 1) {
            extractedFolderName = input.split(" ")[1];
        }
        return extractedFolderName;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(SecretMaster secureNUSData) {
        ArrayList<Secret> secrets;
        try {
            if (folderName.equals("unfiled")) {
                secrets = secureNUSData.listSecrets();
            } else {
                secrets = secureNUSData.listSecrets(folderName);
            }
            if (secrets.isEmpty()) {
                System.out.println("There are no secrets in this folder.");
                return;
            }
            System.out.println("List of secrets:");
            for (Secret secret : secrets) {
                System.out.println(secret.getName());
            }
        } catch (NonExistentFolderException e) {
            System.out.println("Folder " + folderName + " does not exist.");
        }
    }
}
