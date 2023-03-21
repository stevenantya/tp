package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exceptions.ExceptionMain;
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
        secureNUSData = Backend.initialisation();
    }

    public static void main(String[] args) throws FolderExistsException, IllegalFolderNameException,
            IllegalSecretNameException, SecretNotFoundException {
            
        Duke duke = new Duke();
        duke.run();

    }

    public void run() throws IllegalFolderNameException, IllegalSecretNameException, SecretNotFoundException {
        Ui.greetUser();

        boolean isExit = false;

        while (!isExit) {
            Command c = parseCommand();
            Ui.printLine(); //middle line
            isExit = executeCommand(c);
            
            Ui.printLine(); //end line
        }
        Backend.updateStorage(this.secureNUSData.listSecrets());
    }

    public Command parseCommand() {
        String command = Ui.readCommand();
        Ui.printLine(); //top most line
        return Parser.parse(command);
    }

    public boolean executeCommand(Command command) throws IllegalFolderNameException, IllegalSecretNameException,
            SecretNotFoundException {
        if (command != null) {
            try {
                command.execute(secureNUSData);
                return command.isExit();
            } catch (ExceptionMain e) {
                Ui.printError(e.getMessage());
            }
        }
        return false;
    }
}
