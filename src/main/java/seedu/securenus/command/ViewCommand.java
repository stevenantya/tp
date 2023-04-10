package seedu.securenus.command;

import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.NullSecretException;
import seedu.securenus.exceptions.secrets.SecretNotFoundException;
import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

import java.util.HashSet;

/**
 * Represents a class to give a command to view a specific secret.
 */
public class ViewCommand extends Command {
    private String passwordName;

    /**
     * Constructs a ViewCommand object.
     *
     * @param input the user input to extract the password name
     */
    public ViewCommand(String input, HashSet<String> usedNames) throws NullSecretException,
            IllegalSecretNameException {
        this.passwordName = extractName(input);
        nameCheck(passwordName);
        if (!usedNames.contains(passwordName)) {
            Ui.inform("No such secret found.");
        }
    }

    /**
     * Extracts the password name from the user input.
     *
     * @param input the user input containing the password name
     * @return the password name
     */
    public String extractName(String input) {
        return super.extractName(input, "view");
    }

    /**
     * Executes the view command to reveal the password of a specific secret.
     *
     * @param secureNUSData the SecretMaster object containing the secret to view
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
        }
    }
}
