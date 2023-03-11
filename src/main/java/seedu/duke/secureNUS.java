package seedu.duke;

import seedu.duke.Command.Command;
import seedu.duke.storage.SecretMaster;

public class secureNUS {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private final Ui ui;
    private SecretMaster secureNUSData;
    public secureNUS() {
        ui = new Ui();
        secureNUSData = new SecretMaster();
    }

    public static void main(String[] args) {
        new secureNUS().run();
    }

    public void run() {
        ui.greetUser();

        boolean isExit = false;

        while (!isExit) {

            Command c = parseCommand();
            ui.printLine();
            isExit = executeCommand(c);

            ui.printLine();
        }
    }

    public Command parseCommand() {
        String command = ui.readCommand();
        ui.printLine();
        return Parser.parse(command);
    }

    public boolean executeCommand(Command command) {
        if (command != null) {
            command.execute(ui, secureNUSData);
            return command.isExit();
        }
        return false;
    }

}
