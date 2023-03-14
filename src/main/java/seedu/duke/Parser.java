package seedu.duke;

import seedu.duke.command.AddBasicPasswordCommand;
import seedu.duke.command.AddNUSNetCommand;
import seedu.duke.command.AddStudentIDCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.Command;
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
        /*
        else if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        }
        */
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
