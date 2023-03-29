package seedu.duke.command;

import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.storage.SecretMaster;

/**
 * The abstract class Command serves as a blueprint for all other command classes to inherit from. It contains two
 * abstract methods: isExit and execute, which must be implemented by any child classes.
 */
public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute (SecretMaster secureNUSData) throws SecretNotFoundException, ExceptionMain;
}
