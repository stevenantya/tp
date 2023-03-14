package seedu.duke.command;

import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.storage.SecretMaster;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute (SecretMaster secureNUSData) throws SecretNotFoundException;
}
