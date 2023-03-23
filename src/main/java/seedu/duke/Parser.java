package seedu.duke;

import seedu.duke.command.*;

public class Parser {
    public static Command parse(String command) {

        /*if (command.startsWith("new o/NUSNet")) {
            return new AddNUSNetCommand(command);
        } else if (command.startsWith("new o/StudentID")) {
            return new AddStudentIDCommand(command);
        }*/
        /*else if (command.startsWith("new o/CryptoWallet")) {
            return new AddStudentIDCommand(command);
        }*/
         if (command.startsWith("new o/CreditCard")) {
            return new AddCreditCardCommand(command);
        } else if (command.startsWith("new o/CryptoWallet")) {
            return new AddStudentIDCommand(command); // Have to change to AddCryptoWalletCommand
        } else if (command.startsWith("new o/NUSNet")) {
            return new AddNUSNetCommand(command);
        } else if (command.startsWith("new o/StudentID")) {
            return new AddStudentIDCommand(command);
        } else if (command.startsWith("new o/WifiPassword")) {
            return new AddStudentIDCommand(command); // Have to change to AddWifiPasswordCommand
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
        } else if (command.startsWith("bye")) {
            return new ExitCommand();
        } else {
            // represents accidental wrong input
            return new ExitCommand();
        }
    }


}
