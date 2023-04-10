package seedu.securenus.ui;

import seedu.securenus.SecureNUSLogger;
import seedu.securenus.messages.OperationMessages;

import java.util.Scanner;
import java.util.logging.Level;

/**
 * Ui class to handle all user interface printing.
 */
public class Ui {
    public static final String DUPLICATE_WHITESPACE_FMT = "\\s+";

    public static Scanner in = new Scanner(System.in);

    /**
     * Greets the user upon start up of the application.
     */
    public static void greetUser() {
        System.out.println(OperationMessages.INITIALISE);
    }

    /**
     * Prints a warning message to inform the user that the data stored in the application is corrupted.
     */
    public static void printCorruptedDataMessage() {
        System.out.println("    ____________");
        System.out.println("    | ALERT !! |");
        System.out.println("    ------------");
        System.out.println("Data stored is corrupted. Manual editing from the database.txt is not allowed!");
        System.out.println("1. Enter 'save' or continue this session to discard all previous data   OR");
        System.out.println("2. Press Ctrl + C to exit the application and " +
                "manually revert the data in database.txt back to it's last saved state");
        System.out.println();
    }

    /**
     * Prints a message to inform the user that their data from the previous session has been successfully loaded.
     */
    public static void printValidDataMessage() {
        System.out.println("---------------------------------------------------");
        System.out.println("| User data from previous session has been loaded |");
        System.out.println("---------------------------------------------------");
        System.out.println();
    }

    /**
     * Prints a message to inform the user that a new session has started.
     */
    public static void printNewSessionMessage() {
        System.out.println("-----------------------------");
        System.out.println("| A new session has started |");
        System.out.println("-----------------------------");
        System.out.println();
    }

    /**
     * Prints a horizontal line to separate UI elements.
     */
    public static void printLine() {
        System.out.print("_____________________________________________________\n");
    }

    /**
     * Prints an informative message surrounded by two lines of dashes.
     *
     * @param message the informative message to be printed
     */
    public static void inform(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Reads the user's command from the console.
     *
     * @return String containing user's command.
     */
    public static String readCommand() {
        while (in.hasNextLine()) {
            String line = in.nextLine();
            SecureNUSLogger.LOGGER.log(Level.INFO, "command, " + removeExtraWhiteSpaces(line));
            return removeExtraWhiteSpaces(line);
        }
        return "";
    }

    /**
     * Reads a line of input from the user through the console.
     *
     * @return a String containing the line of input entered by the user, or an empty String if no input is available
     */
    public static String readLine() {
        while (in.hasNextLine()) {
            String line = in.nextLine();
            SecureNUSLogger.LOGGER.log(Level.INFO, "input, " + line);
            return line;
        }
        return "";
    }

    /**
     * Removing the trailing backslashes
     * This is commonly used when preparing strings for use in regular expressions or when saving to a file.
     *
     * @param input the string to remove backslashes from
     * @return the input string with all backslashes removed
     */
    public static String removeBackSlashes(String input) {
        return input.replace("\\", "\\\\");
    }

    /**
     * Removes any extra white spaces from the given string, such as consecutive duplicate spaces and leading/trailing
     * spaces.
     *
     * @param line the input string to remove extra white spaces from
     * @return the modified string with extra white spaces removed
     */
    public static String removeExtraWhiteSpaces(String line) {
        // remove duplicate whitespaces
        line = line.replaceAll(DUPLICATE_WHITESPACE_FMT, " ");
        return line.trim(); // remove leading and trailing whitespaces
    }

    /**
     * Prints a message to inform the user to enter a command.
     */
    public static void informUserToStartCommand() {
        System.out.print("Enter Command:");
    }

    /**
     * Prints error message to console.
     *
     * @param message Error message to be printed.
     */
    public static void printError(String message) {
        System.out.println("Oops! Error encountered: "+ message);
    }

    /**
     * Prints out a message to the console to inform the user that an operation has been cancelled.
     */
    public static void informOperationCancel () {
        System.out.println(OperationMessages.CANCEL_OPERATION);
    }

    /**
     * Closes the scanner used for user input.
     */
    public static void close() {
        in.close();
    }
}
