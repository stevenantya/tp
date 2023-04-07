package seedu.duke.command;

import seedu.duke.Backend;
import seedu.duke.messages.OperationMessages;
import seedu.duke.storage.SecretMaster;
import seedu.duke.ui.Ui;

/**
 * Class that allows users to save whatever output keyed in so far
 */
public class SaveCommand extends Command {

    /**
     * Executes the save command, which writes all data in the session into the database.
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        assert secureNUSData != null;
        Ui.inform(OperationMessages.SAVING);
        Backend.updateStorage(secureNUSData.listSecrets());
        Ui.inform(OperationMessages.SAVE_COMPLETE);
    }

    /**
     * Returns false the ListCommand is an exit command which shows that the code does not exit when list is called
     *
     * @return always false for ListCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
