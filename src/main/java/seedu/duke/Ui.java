package seedu.duke;

import java.util.Scanner;

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
        System.out.println("Welcome to secureNUS v1.0\n" +
                "Current Features\n" +
                "Adding a password      : new [NAME] /f [FOLDER_NAME]\n" +
                "Adding a NUSNet ID     : new o/NUSNet [NAME] /f [FOLDER_NAME]\n" +
                "Adding a Student ID    : new o/StudentID [NAME] /f [FOLDER_NAME]\n");
    }

    /**
     * Prints a horizontal line to separate UI elements.
     */
    public static void printLine() {
        System.out.print("_____________________________________________________\n");
    }

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
            return removeExtraWhiteSpaces(line);
        }
        return "";
    }

    public static String readLine() {
        while (in.hasNextLine()) {
            String line = in.nextLine();
            return line;
        }
        return "";
    }

    public static String removeExtraWhiteSpaces(String line) {
        // remove duplicate whitespaces
        line = line.replaceAll(DUPLICATE_WHITESPACE_FMT, " ");
        return line.trim(); // remove leading and trailing whitespaces
    }

    // TODO can this be removed with logging?
    /**
     * Prints error message to console.
     *
     * @param message Error message to be printed.
     */
    public static void printError(String message) {
        System.out.println("Oops! Error encountered "+ message);
    }





    public static void close() {
        in.close();
    }
}
