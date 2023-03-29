package seedu.duke;

import seedu.duke.command.AddBasicPasswordCommand;
import seedu.duke.command.AddNUSNetCommand;
import seedu.duke.command.AddStudentIDCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.SearchCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.exceptions.InvalidCommandException;

public class Parser {
    public static Command parse(String command) throws InvalidCommandException {

        if (command.startsWith("new o/NUSNet")) {
            return new AddNUSNetCommand(command);
        } else if (command.startsWith("new o/StudentID")) {
            return new AddStudentIDCommand(command);
        } else if (command.startsWith("new o/CryptoWallet")) {
            return new AddStudentIDCommand(command);
        } else if (command.startsWith("new o/")) {
            System.out.println("o/ option is invalid\nValid options are: \nnew o/NUSNet\nnew o/Student ID\nnew o/CryptoWallet");
            throw new InvalidCommandException();
        } else if (command.startsWith("new")) {
            return new AddBasicPasswordCommand(command);
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        } else if (command.startsWith("list")) {
            return new ListCommand(command);
        } else if (command.startsWith("search")) {
            return new SearchCommand(command);
        } else if (command.startsWith("view")) {
            return new ViewCommand(command);
        } else if (command.startsWith("edit")) {
            return new EditCommand(command);
        } else if (command.startsWith("exit") || command.startsWith("bye")) {
            return new ExitCommand();
        } else {
            // represents accidental wrong input
            throw new InvalidCommandException();
        }
    }


}
