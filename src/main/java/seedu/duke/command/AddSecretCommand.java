package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

public class AddSecretCommand extends Command {
    private String name;
    private String folderName;

    public AddSecretCommand(String input) {
        name = extractName(input);
        folderName = extractFolderName(input);
    }
    public String extractName(String input) {
        String extractedName = input.split("new ")[1];
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

    public String inquire(String question) {
        System.out.println(question);
        String result = Ui.readCommand();
        return result;
    }

    @Override
    public void execute(SecretMaster secureNUSData) {
        Secret secret = new Secret(name,folderName);
        try {
            secureNUSData.addSecret(secret);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException | IllegalSecretNameException | IllegalFolderNameException e) {
            throw new RuntimeException(e);
        }
        String starsPassword = "*".repeat(8);
        System.out.println("I have added a new Secret:\n");
        System.out.println("name     = " + name + "\n" +
                "folder   = " + folderName + "\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
