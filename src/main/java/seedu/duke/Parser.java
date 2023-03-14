package seedu.duke;

import seedu.duke.Command.AddBasicPasswordCommand;
import seedu.duke.Command.AddStudentIDCommand;
import seedu.duke.Command.AddNUSNetCommand;
import seedu.duke.Command.Command;
import seedu.duke.Command.DeleteCommand;
import seedu.duke.Command.ListCommand;
import seedu.duke.Command.ExitCommand;

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
        else if (command.startsWith("list")) {
            return new ListCommand(command);
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
