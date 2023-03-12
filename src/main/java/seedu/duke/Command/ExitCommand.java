package seedu.duke.Command;

import seedu.duke.Ui;
import seedu.duke.storage.SecretMaster;

/**
 * @author : Steven A. O. Waskito
 **/
public class ExitCommand extends Command{

    public ExitCommand() {

    }
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(SecretMaster secureNUSData) {

    }
}
