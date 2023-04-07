package seedu.duke.command;

import seedu.duke.exceptions.NullFolderException;
import seedu.duke.exceptions.secrets.FolderNotFoundException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.NonExistentFolderException;

import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.CreditCard;
import seedu.duke.secrets.CryptoWallet;
import seedu.duke.secrets.NUSNet;
import seedu.duke.secrets.StudentID;
import seedu.duke.secrets.WifiPassword;
import seedu.duke.ui.Ui;


import java.util.ArrayList;
import java.util.HashSet;

/**
 * The ListCommand class represents the command to list all secrets in a specific folder or all secrets if no folder
 * is specified.
 */
public class ListCommand extends Command {

    private String folderName;

    /**
     * Constructs a new ListCommand object with the given input.
     *
     * @param input the input string containing the folder name, if specified.
     */
    public ListCommand(String input, HashSet<String> folders) throws FolderNotFoundException,
            IllegalFolderNameException, NullFolderException {
        assert input != null;
        if (input.equals("list")) {
            folderName = null;
        } else {
            this.folderName = extractFolderName(input);
            if (folderName == null) {
                throw new FolderNotFoundException();
            }
            folderCheckWithExistence(folderName, folders);
        }
    }

    public String extractFolderName(String input) {
        assert input != null;
        String extractedFolderName = null;
        if (input.split(" f/").length > 1) {
            extractedFolderName = input.split(" f/")[1];
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
    @SuppressWarnings("checkstyle:LocalVariableName")
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
     * Executes the ListCommand by listing all secrets or secrets in a specific folder.
     *
     * @param secureNUSData the SecretMaster object to execute the command on
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
                // should not reach here since these checks are done above
                Ui.inform("There are no secrets in this folder: " + folderName);
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
