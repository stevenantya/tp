package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

/**
 * Represents a class to give a command to delete a secret from the secureNUSData.
 * Inherits from the Command class.
 */
public class DeleteCommand extends Command {

    private String secretName;
    private String[] secretNames;

    /**
     * Class constructor that extracts the name of the secret and its folder from the input string.
     *
     * @param input the input string from the user
     */
    public DeleteCommand(String input) {
        this.secretNames = extractName(input);
    }

    /**
     * Extracts the name of the secret to be deleted from the input string.
     *
     * @param input the input string from the user
     * @return the name of the secret to be deleted
     */
    public String[] extractName(String input) {
        String extractedName = input.split("delete ")[1].strip();
        String[] extractedNames = extractedName.split("p/");
        for (int ix = 1; ix < extractedNames.length; ix += 1) {
            extractedNames[ix] = extractedNames[ix].strip();
        }
        return extractedNames;
    }

    /**
     * Deletes the specified secret from the secureNUSData.
     *
     * @param secureNUSData the SecretMaster object that stores all the secrets.
     * @throws SecretNotFoundException if the secret to be deleted is not found in the secureNUSData.
     */
    @Override
    public void execute(SecretMaster secureNUSData) throws SecretNotFoundException {
        for (int index = 1; index < secretNames.length; index += 1) {
            secretName = secretNames[index];
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
                System.out.println("You deleted " + secretName);
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

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false, as it is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
