package seedu.duke.command;

import seedu.duke.Backend;
import seedu.duke.exceptions.InsufficientParamsException;
import seedu.duke.ui.Ui;
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
    public DeleteCommand(String input) throws InsufficientParamsException {
        this.secretNames = extractName(input);
        if (secretNames.length == 0) {
            throw new InsufficientParamsException();
        }
    }

    /**
     * Extracts the name of the secret to be deleted from the input string.
     *
     * @param input the input string from the user
     * @return the name of the secret to be deleted
     */
    public String[] extractName(String input) {
        assert input != null;
        String extractedName = input.split("delete ")[1].strip();
        String[] extractedNames = extractedName.split(" ");
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
        assert secureNUSData != null;
        for (int index = 0; index < secretNames.length; index += 1) {
            secretName = secretNames[index];
            if (Secret.isIllegalName(secretName)) {
                Ui.inform("Invalid Secret Name: " + secretName + ". Skipping this input");
                continue;
            }
            Secret deleteData = null;
            boolean isValid = false;
            try {
                deleteData = secureNUSData.getByName(secretName);
                isValid = true;
            } catch (SecretNotFoundException e) {
                Ui.inform("Secret Not Found: " + secretName + ". Skipping this input");
                isValid = false;
            }
            if (isValid && (deleteData != null)) {
                try {
                    secureNUSData.removeSecret(deleteData);
                    System.out.println("Successfully deleted: " + secretName);

                } catch (SecretNotFoundException e) {
                    Ui.printError("Secret Not Found: " + secretName);
                }
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
