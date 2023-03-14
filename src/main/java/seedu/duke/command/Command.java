package seedu.duke.command;

import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.storage.SecretMaster;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute (SecretMaster secureNUSData) throws IllegalFolderNameException,
            IllegalSecretNameException;
}
