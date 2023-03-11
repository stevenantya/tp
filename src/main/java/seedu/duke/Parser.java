package seedu.duke;

import seedu.duke.Command.*;

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
