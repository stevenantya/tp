package seedu.duke.command;

import seedu.duke.Ui;
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
        String extractedFolderName = "unnamed";
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
    public void execute(SecretMaster secureNUSData) throws SecretNotFoundException {
        Secret deleteData = null;
        boolean isValid = false;
        try {
            deleteData = secureNUSData.getByName(secretName);
            isValid = true;
        } catch (SecretNotFoundException e) {
            Ui.printError("Data not found!");
            isValid = false;
        }
        if (isValid && (deleteData != null)) {
            System.out.println("You deleted " + secretName + " in folder: " + folderName);
            try {
                secureNUSData.removeSecret(deleteData);
            } catch (SecretNotFoundException e) {
                Ui.printError("Data not found!");
            }
        } else {
            System.out.println("Please enter a valid secret name!");
        }
    }
}
