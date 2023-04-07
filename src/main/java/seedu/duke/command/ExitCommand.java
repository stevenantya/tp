package seedu.duke.command;

import seedu.duke.messages.OperationMessages;
import seedu.duke.storage.SecretMaster;
import seedu.duke.ui.Ui;

/**
 * Represents a class to give a command to exit the password manager
 */
public class ExitCommand extends Command {

    /**
     * Creates an instance of ExitCommand.
     */
    public ExitCommand() {
    }

    /**
     * Executes the exit command by doing nothing.
     *
     * @param secureNUSData The secret master data to execute the command on
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        Ui.inform(OperationMessages.CLOSE);
    }

    /**
     * Returns true to indicate that this command is an exit command.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
