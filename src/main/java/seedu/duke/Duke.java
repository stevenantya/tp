package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.storage.SecretMaster;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private SecretMaster secureNUSData;
    public Duke() throws FolderExistsException, IllegalFolderNameException {
        secureNUSData = new SecretMaster();
    }

    public static void main(String[] args) throws FolderExistsException, IllegalFolderNameException,
            IllegalSecretNameException {
        
        Duke duke = new Duke();
        duke.run();
    }

    public void run() throws IllegalFolderNameException, IllegalSecretNameException {
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

    public boolean executeCommand(Command command) throws IllegalFolderNameException, IllegalSecretNameException {
        if (command != null) {
            command.execute(secureNUSData);
            return command.isExit();
        }
        return false;
    }

}
