package seedu.securenus.command;

import seedu.securenus.Backend;
import seedu.securenus.exceptions.InsufficientParamsException;
import seedu.securenus.ui.Ui;
import seedu.securenus.exceptions.secrets.SecretNotFoundException;
import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretMaster;

/**
 * Represents a class to give a command to delete a secret from the secureNUSData.
 * Inherits from the Command class.
 */
public class DeleteCommand extends Command {

    private static final String SECRET_DELIMITER = "delete ";
    private String[] secretNames;

    /**
     * Creates a new instance of DeleteCommand with the given input string.
     *
     * @param input the input string containing the names of secrets to be deleted
     * @throws InsufficientParamsException if no secret names are provided for deletion
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
        String extractedName = input.split(SECRET_DELIMITER)[1].strip();
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
        String secretName;
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
                    Ui.inform("Successfully deleted: " + secretName);

                } catch (SecretNotFoundException e) {
                    Ui.printError("Secret Not Found: " + secretName);
                }
            }
        }
        Backend.updateStorage(secureNUSData.listSecrets());
    }
}
