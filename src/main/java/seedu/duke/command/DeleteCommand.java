package seedu.duke.command;

import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;


public class DeleteCommand extends Command {

    private String secretName;
    private String folderName;
    public DeleteCommand(String input) {
        this.secretName = extractName(input);
        this.folderName = extractFolderName(input);
    }
    public String extractName(String input) {
        String extractedName = input.split("delete ")[1];
        extractedName = extractedName.split(" /f")[0];
        return extractedName;
    }
    public String extractFolderName(String input) {
        String extractedFolderName = "unfiled";
        if (input.split("/f ").length > 1) {
            extractedFolderName = input.split("/f ")[1];
        }
        return extractedFolderName;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(SecretMaster secureNUSData) throws SecretNotFoundException, SecretNotFoundException {
        Secret deleteData = secureNUSData.getByName(secretName);

        secureNUSData.removeSecret(deleteData);
        System.out.println("You deleted " + secretName + " in " + folderName + "\n");
    }
}
