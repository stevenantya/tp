package seedu.duke;

import seedu.duke.Command.Command;
import seedu.duke.storage.SecretMaster;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private SecretMaster secureNUSData;
    public Duke() {
        secureNUSData = new SecretMaster();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Ui.greetUser();

        boolean isExit = false;

        while (!isExit) {

            Command c = parseCommand();
            Ui.printLine();
            isExit = executeCommand(c);

            Ui.printLine();
        }
    }

    public Command parseCommand() {
        String command = Ui.readCommand();
        Ui.printLine();
        return Parser.parse(command);
    }

    public boolean executeCommand(Command command) {
        if (command != null) {
            command.execute(secureNUSData);
            return command.isExit();
        }
        return false;
    }

}
