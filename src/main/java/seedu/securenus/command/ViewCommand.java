package seedu.securenus.command;

import seedu.securenus.SecureNUSLogger;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.NullSecretException;
import seedu.securenus.exceptions.secrets.SecretNotFoundException;
import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

import java.util.HashSet;
import java.util.logging.Level;

/**
 * Represents a class to give a command to view a specific secret.
 */
public class ViewCommand extends Command {
    private String passwordName;

    /**
     * Constructs a ViewCommand object with the given input and set of used secret names.
     *
     * @param input the input string containing the password name to be viewed
     * @param usedNames the set of names of secrets that have already been created
     * @throws NullSecretException if the password name extracted from the input is null
     * @throws IllegalSecretNameException if the password name extracted from the input is illegal
     */
    public ViewCommand(String input, HashSet<String> usedNames) throws NullSecretException,
            IllegalSecretNameException, SecretNotFoundException {
        this.passwordName = extractName(input);
        nameCheck(passwordName);
        if (!usedNames.contains(passwordName)) {
            throw new SecretNotFoundException();
        }
    }

    /**
     * Extracts the name from the given input string, with the specified keyword.
     *
     * @param input the input string to extract the name from
     * @return the extracted name from the input string
     * @throws NullPointerException if the input string is null
     * @throws IllegalArgumentException if the specified keyword is null or empty
     */
    public String extractName(String input) {
        return super.extractName(input, "view");
    }


    /**
     * Executes the "view" command with the specified secureNUSData object.
     *
     *  @param secureNUSData the secureNUSData object to execute the command on
     * @throws NullPointerException if the secureNUSData object is null
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        Secret passwordSecret;
        try {
            passwordSecret = secureNUSData.getByName(this.passwordName);
            assert passwordSecret != null;
            Ui.inform(passwordSecret.getRevealStr());
        } catch (SecretNotFoundException e) {
            Ui.inform("There are no passwords that matches that name!\n" +
                    "Make sure you follow this format: \"view PASSWORD_NAME\"");
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, secret not found, " + this.passwordName);
        }
    }
}
