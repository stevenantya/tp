package seedu.duke.Command;

import seedu.duke.Ui;
import seedu.duke.secrets.Secret;

public class AddPasswordCommand extends Command {

    private final Secret password;

    public AddPasswordCommand(Secret password) {
        this.password = password;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui) {

    }
}
