package seedu.duke.command;

import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;
import seedu.duke.ui.Ui;

import java.util.HashSet;

/**
 * Represents a class to give a command to view a specific secret.
 */
public class ViewCommand extends Command {
    private final String passwordName;

    /**
     * Constructs a ViewCommand object.
     *
     * @param input the user input to extract the password name
     */
    public ViewCommand(String input, HashSet<String> usedNames){
        this.passwordName = extractName(input);
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
            Ui.inform(passwordSecret.getRevealStr());
        } catch (SecretNotFoundException e) {
            Ui.inform("There are no passwords that matches that name!\n" +
                    "Make sure you follow this format: \"view PASSWORD_NAME\"");
        }
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return false as view command is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
