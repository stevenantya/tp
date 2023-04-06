package seedu.duke.command;

import seedu.duke.storage.SecretMaster;
import seedu.duke.ui.Ui;

/**
 * Class that displays the list of all available commands in a menu format.
 */
public class MenuCommand extends Command {

    /**
     * Displays the list of all available commands in a menu format.
     */
    public void displayMenu() {
        Ui.inform("SecureNUS Command Menu:\n" +
                "1. Add a general password: new [PASSWORD_NAME] [f/FOLDER_NAME]\n" +
                "\tAdd Credit Card details: " +
                "new o/CreditCard [NAME] [f/FOLDER_NAME]\n" +
                "\tAdd details for a Crypto Wallet: " +
                "new o/Crypto [NAME] [f/FOLDER_NAME]\n" +
                "\tAdd a NUSNet ID and Password: " +
                "new o/NUSNet [NAME] [f/FOLDER_NAME]\n" +
                "\tAdd a NUS StudentID details (starting with A and ends with capital Alphabet): " +
                "new o/StudentID [NAME] [f/FOLDER_NAME]\n" +
                "\tAdd a password for Wifi: " +
                "new o/WifiPassword [NAME] [f/FOLDER_NAME]\n" +
                "2. Delete the unwanted secrets: delete\n" +
                "3. List all the secrets: list [f/FOLDER_NAME]\n" +
                "4. View secrets: view PASSWORD_NAME\n" +
                "5. Search for secrets: search n/NAME_LIKE [f/FOLDER]\n" +
                "6. Export the secrets in .txt file: save\n" +
                "7. Edit a secrets: edit PASSWORD_NAME\n" +
                "8. Menu list to refer for commands: menu\n" +
                "9. Exit from the password manager (will automatically save this session): exit\n" +
                "10. To cancel the operation at any point: c/"
        );
    }

    /**
     * Executes the menu command, which displays the list of all available commands.
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        displayMenu();
    }

    /**
     * Returns false the ListCommand is an exit command which shows that the code does not exit when list is called
     *
     * @return always false for ListCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
