package seedu.securenus.command;

import seedu.securenus.Backend;
import seedu.securenus.messages.OperationMessages;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

/**
 * Class that allows users to save whatever output keyed in so far
 */
public class SaveCommand extends Command {

    /**
     * Executes the command to save the list of secrets to the storage.
     *
     * @param secureNUSData The instance of SecretMaster class that stores the list of secrets.
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        assert secureNUSData != null;
        Ui.inform(OperationMessages.SAVING);
        Backend.updateStorage(secureNUSData.listSecrets());
        Ui.inform(OperationMessages.SAVE_COMPLETE);
    }
}
