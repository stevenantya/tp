package seedu.duke;

import java.util.Scanner;

/**
 * Ui class to handle all user interface printing.
 */
public class Ui {

    /**
     * Greets the user upon start up of the application.
     */
    public static void greetUser() {
        System.out.println("Welcome to secureNUS v1.0\n" +
                "Current Features\n" +
                "Adding a password      : new [NAME] /f [FOLDER_NAME]\n" +
                "Adding a NUSNet ID     : new o/NUSNet [NAME] /f [FOLDER_NAME]\n" +
                "Adding a Student ID    : new o/StudentID [NAME] /f [FOLDER_NAME] \n");
    }

    /**
     * Prints a horizontal line to separate UI elements.
     */
    public static void printLine() {
        System.out.print("_____________________________________________________\n");
    }

    /**
     * Reads the user's command from the console.
     *
     * @return String containing user's command.
     */
    public static String readCommand() {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            return in.nextLine();
        }
        in.close();
        return "";
    }

    /**
     * Prints error message to console.
     *
     * @param message Error message to be printed.
     */
    public static void printError(String message) {
        System.out.println("Oops! Error encountered\n"+ message);
    }
}
