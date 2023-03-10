package seedu.duke.Command;

import seedu.duke.Ui;
import seedu.duke.storage.SecretMaster;

/**
 * @author : Steven A. O. Waskito
 **/
public class AddNUSNetCommand extends Command{
    public AddNUSNetCommand(String command) {

    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, SecretMaster secureNUSData) {

    }
}
