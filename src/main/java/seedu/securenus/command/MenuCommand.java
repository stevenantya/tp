package seedu.securenus.command;

import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

/**
 * Class that displays the list of all available commands in a menu format.
 */
public class MenuCommand extends Command {

    /**
     * Displays the list of all available commands in a menu format.
     */
    public void displayMenu() {
        Ui.inform("SecureNUS Command Menu:\n" +
                "1. Add a general password: new SECRET_NAME [f/FOLDER_NAME]\n" +
                "\tAdd Credit Card details: " +
                "new o/CreditCard SECRET_NAME [f/FOLDER_NAME]\n" +
                "\tAdd details for a Crypto Wallet: " +
                "new o/Crypto SECRET_NAME [f/FOLDER_NAME]\n" +
                "\tAdd NUSNet ID and Password (e*******): " +
                "new o/NUSNet SECRET_NAME [f/FOLDER_NAME]\n" +
                "\tAdd NUS StudentID (A*******E): " +
                "new o/StudentID SECRET_NAME [f/FOLDER_NAME]\n" +
                "\tAdd a password for Wifi: " +
                "new o/WifiPassword SECRET_NAME [f/FOLDER_NAME]\n" +
                "2. Delete the unwanted secrets: delete SECRET_NAME_1 [SECRET_NAME_2] [SECRET...]\n" +
                "3. List all the secrets: list [f/FOLDER_NAME]\n" +
                "4. View secrets: view SECRET_NAME\n" +
                "5. Search for secrets: search SECRET_NAME_LIKE [f/FOLDER]\n" +
                "6. Export the secrets to assets/database.txt file: save\n" +
                "7. Edit a secrets: edit SECRET_NAME\n" +
                "8. Menu list to refer for commands: menu\n" +
                "9. Exit from SecureNUS (will automatically save this session): exit\n" +
                "10. To cancel the current operation (e.g. mid adding/ editing password): c/"
        );
    }

    /**
     * Executes the menu command, which displays the list of all available commands.
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        displayMenu();
    }
}
