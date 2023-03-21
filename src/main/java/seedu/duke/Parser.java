package seedu.duke;

import seedu.duke.command.AddBasicPasswordCommand;
import seedu.duke.command.AddNUSNetCommand;
import seedu.duke.command.AddStudentIDCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;

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
        else if (command.startsWith("bye")) {
            return new ExitCommand();
        } //ADD INVALID COMMAND EXCEPTION
        else {
            return new ExitCommand();
        }
    }


}
