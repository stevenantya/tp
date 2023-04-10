package seedu.securenus.command;

import seedu.securenus.exceptions.NullFolderException;
import seedu.securenus.exceptions.secrets.FolderNotFoundException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.NonExistentFolderException;

import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretMaster;

import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.secrets.CreditCard;
import seedu.securenus.secrets.CryptoWallet;
import seedu.securenus.secrets.NUSNet;
import seedu.securenus.secrets.StudentID;
import seedu.securenus.secrets.WifiPassword;
import seedu.securenus.ui.Ui;


import java.util.ArrayList;
import java.util.HashSet;

/**
 * The ListCommand class represents the command to list all secrets in a specific folder or all secrets if no folder
 * is specified.
 */
public class ListCommand extends Command {

    private static final String FOLDER_DELIMITER = " f/";
    private String folderName;

    /**
     * A class representing the "list" command, which lists all secrets in a given folder or all folders.
     *
     * @param input the input string containing the folder name or "list" keyword
     * @param folders the set of existing folder names
     * @throws FolderNotFoundException if the specified folder does not exist
     * @throws IllegalFolderNameException if the specified folder name is illegal
     * @throws NullFolderException if the folder name is null
     */
    public ListCommand(String input, HashSet<String> folders) throws FolderNotFoundException,
            IllegalFolderNameException, NullFolderException {
        assert input != null;
        if (input.equals("list")) {
            // if you want to display all secrets in all folders
            folderName = null;
        } else {
            // specifically for given secret, hence it cannot be null anymore
            this.folderName = extractFolderName(input);
            if (folderName == null) {
                throw new FolderNotFoundException();
            }
            folderCheckWithExistence(folderName, folders);
        }
    }

    /**
     * Extracts the folder name from the user input.
     * If the input does not contain the folder name, returns null.
     *
     * @param input the user input
     * @return the extracted folder name or null if not found
     */
    public String extractFolderName(String input) {
        assert input != null;
        String extractedFolderName = null;
        if (input.split(FOLDER_DELIMITER).length > 1) {
            extractedFolderName = input.split(FOLDER_DELIMITER)[1];
            extractedFolderName = extractedFolderName.split(" ")[0];
        }
        return extractedFolderName;
    }

    /**
     * Masks a string password by replacing its characters with asterisks.
     *
     * @param password the string password to be masked.
     * @return the masked password string.
     */
    String maskStringPassword(String password) {
        return "********";
    }

    /**
     * Masks an integer password by converting it to a string of asterisks with the same length as the integer password.
     *
     * @param password the integer password to be masked.
     * @return the masked password string.
     */
    public String maskIntPasswordAsString(int password) {
        return String.format("%0" + String.valueOf(password).length() + "d", 0).replaceAll("0", "*");
    }

    /**
     * Returns the type and details of the given secret.
     *
     * @param secret the secret to get the type and details of.
     * @return return the type and details of the secret.
     */
    public String getSecretTypeInfo(Secret secret) {
        assert secret != null;
        if (secret instanceof BasicPassword) {
            BasicPassword basicPassword = (BasicPassword) secret;
            return "Type of Secret: Basic Password" + "\n" +
                   "Name: " + basicPassword.getName() + "\n" +
                   "Folder: " + basicPassword.getFolderName() + "\n" +
                   "Username: " + basicPassword.getUsername() + "\n" +
                   "URL: " + basicPassword.getUrl() + "\n" +
                   "Password: " + maskStringPassword(basicPassword.getPassword()) + "\n";

        } else if (secret instanceof CreditCard) {
            CreditCard creditCard = (CreditCard) secret;
            return "Type of Secret: Credit Card" + "\n" +
                    "Name: " + creditCard.getName() + "\n" +
                    "Folder: " + creditCard.getFolderName() + "\n" +
                    "Full Name: " + creditCard.getFullName() + "\n" +
                    "Credit Card Number: " + maskStringPassword(creditCard.getCreditCardNumber()) + "\n" +
                    "CVC Number: " + maskStringPassword(creditCard.getCvcNumber()) + "\n" +
                    "Expiry Date: " + maskStringPassword(creditCard.getExpiryDate())+ "\n";

        } else if (secret instanceof CryptoWallet) {
            CryptoWallet cryptoWallet = (CryptoWallet) secret;
            return "Type of Secret: CryptoCurrency Wallet" + "\n" +
                    "Name: " + cryptoWallet.getName() + "\n" +
                    "Folder: " + cryptoWallet.getFolderName() + "\n" +
                    "Username: " + cryptoWallet.getUsername() + "\n" +
                    "Private Key: " + maskStringPassword(cryptoWallet.getPrivateKey()) + "\n" +
                    "Seed Phrase: " + maskStringPassword(cryptoWallet.getSeedPhrase())+ "\n";

        } else if (secret instanceof NUSNet) {
            NUSNet nusNet = (NUSNet) secret;
            return "Type of Secret: NUSNet ID" + "\n" +
                    "Name: " + nusNet.getName() + "\n" +
                    "Folder: " + nusNet.getFolderName() + "\n" +
                    "NUSNet ID: " + nusNet.getNusNetId() + "\n" +
                    "Password: " + maskStringPassword(nusNet.getPassword()) + "\n";
                    
        } else if (secret instanceof StudentID) {
            StudentID studentID = (StudentID) secret;
            return "Type of Secret: Student ID" + "\n" +
                    "Name: " + studentID.getName() + "\n" +
                    "Folder: " + studentID.getFolderName() + "\n" +
                    "Student ID: " + maskStringPassword(studentID.getStudentID())+ "\n";

        } else if (secret instanceof WifiPassword) {
            WifiPassword wifiPassword = (WifiPassword) secret;
            return "Type of Secret: Wifi Password" + "\n" +
                    "Name: " + wifiPassword.getName() + "\n" +
                    "Folder: " + wifiPassword.getFolderName() + "\n" +
                    "Username: " + wifiPassword.getUsername() + "\n" +
                    "Password: " + maskStringPassword(wifiPassword.getPassword())+ "\n";

        } else {
            return "Secret";
        }
    }

    /**
     * Executes the 'list' command to display a list of secrets stored in the secret master.
     * If a folder name is specified, it only displays secrets within that folder.
     *
     * @param secureNUSData the SecretMaster object that stores the secrets.
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        ArrayList<Secret> secrets;
        try {
            if (folderName == null) {
                secrets = secureNUSData.listSecrets();
            } else {
                secrets = secureNUSData.listSecrets(folderName);
            }
            if (secrets.isEmpty()) {
                if (folderName == null) {
                    Ui.inform("You have no secrets stored yet.");
                } else {
                    // should not reach here since these checks are done above
                    Ui.inform("There are no secrets in this folder: " + folderName);
                }
                return;
            }
            Ui.printLine();
            System.out.println("List of secrets:");
            int counter = 1;
            for (Secret secret : secrets) {
                String secretTypeInfo = getSecretTypeInfo(secret);
                System.out.println(counter + ". " + secretTypeInfo);
                counter += 1;
            }
            Ui.printLine();
        } catch (NonExistentFolderException e) {
            Ui.printError("Folder " + folderName + " does not exist.");
        }
    }
}
