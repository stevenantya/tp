package seedu.duke;

import seedu.duke.Command.Command;
import seedu.duke.exceptions.SecretNotFoundException;
import seedu.duke.storage.SecretMaster;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private SecretMaster secureNUSData;
    public Duke() {
        secureNUSData = new SecretMaster();
    }

    public static void main(String[] args) throws SecretNotFoundException {
        new Duke().run();
    }

    public void run() throws SecretNotFoundException {
        Ui.greetUser();

        boolean isExit = false;

        while (!isExit) {

            Command c = parseCommand();
            Ui.printLine(); //middle line
            isExit = executeCommand(c);

            Ui.printLine(); //end line
        }
    }

    public Command parseCommand() {
        String command = Ui.readCommand();
        Ui.printLine(); //top most line
        return Parser.parse(command);
    }

    public boolean executeCommand(Command command) throws SecretNotFoundException {
        if (command != null) {
            command.execute(secureNUSData);
            return command.isExit();
        }
        return false;
    }

}
