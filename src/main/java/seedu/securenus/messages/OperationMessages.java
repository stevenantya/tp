package seedu.securenus.messages;

/**
 * Contains operation messages for the application.
 */
public class OperationMessages {
    public static final String LOGO =
            "     .--------.\n" +
            "    / .------. \\\n" +
            "   / /        \\ \\\n" +
            "   | |        | |\n" +
            "  _| |________| |_\n" +
            ".' |_|        |_| '.\n" +
            "'._____ ____ _____.'\n" +
            "|     .'____'.     |\n" +
            "'.__.'.'    '.'.__.'\n" +
            "'.__  | NUS  |  __.'\n" +
            "|   '.'.____.'.'   |\n" +
            "'.____'.____.'____.'\n" +
            "'.________________.'\n\n";
    public static final String INITIALISE =
            LOGO +
            "Welcome to secureNUS v2.1\n" +
            "Try out some of these commands:\n" +
            "Adding a password      : new SECRET_NAME [f/FOLDER_NAME]\n" +
            "Adding a NUSNet ID     : new o/NUSNet SECRET_NAME [f/FOLDER_NAME]\n" +
            "Adding a Student ID    : new o/StudentID SECRET_NAME [f/FOLDER_NAME]\n" +
            "Adding a Credit Card   : new o/CreditCard SECRET_NAME [f/FOLDER_NAME]\n" +
            "Adding a Wifi Password : new o/WifiPassword SECRET_NAME [f/FOLDER_NAME]\n" +
            "Adding a Crypto Wallet : new o/Crypto SECRET_NAME [f/FOLDER_NAME]\n" +
            "Viewing all commands   : menu\n";

    public static final String SAVING = "Saving secrets to storage, please do not close application...";
    public static final String SAVE_COMPLETE = "Save complete. All secrets saved into assets/database.txt.\nIf " +
            "exporting to another device, save this file into the root directory's assets/database.txt before you " +
            "start me again!";

    public static final String CLOSE = "Thank you for using me. I hope you enjoyed the time!";
    public static final String CANCEL_OPERATION = "Operation Cancelled.";
}
