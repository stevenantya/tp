package seedu.duke;

import seedu.duke.Command.AddBasicPasswordCommand;
import seedu.duke.Command.AddNUSNetCommand;
import seedu.duke.Command.AddStudentIDCommand;
import seedu.duke.Command.DeleteCommand;
import seedu.duke.Command.ExitCommand;
import seedu.duke.Command.Command;
public class Parser {
    public static Command parse(String command) {

        if (command.startsWith("new o/NUSNet")) {
            return new AddNUSNetCommand(command);
        }
        else if (command.startsWith("new o/StudentID")) {
            return new AddStudentIDCommand(command);
        }
        else if (command.startsWith("new")) {
            return new AddBasicPasswordCommand(command);
        }
        else if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        }

        /*
        else if (command.startsWith("new o/StudentID")) {
            return new AddStudentIDCommand(command);
        }
        else if (command.startsWith("bye")) {
            return new ExitCommand();
        }
        */
        else {
            return new ExitCommand();
        }
    }


}
