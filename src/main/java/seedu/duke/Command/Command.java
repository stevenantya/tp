package seedu.duke.Command;

import seedu.duke.Ui;
import seedu.duke.storage.SecretMaster;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute (SecretMaster secureNUSData);
}
