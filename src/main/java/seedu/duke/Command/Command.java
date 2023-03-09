package seedu.duke.Command;

import seedu.duke.Ui;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute (Ui ui);
}
