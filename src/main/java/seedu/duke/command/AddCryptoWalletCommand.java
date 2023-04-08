package seedu.duke.command;

import seedu.duke.Backend;
import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.OperationCancelException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.messages.InquiryMessages;
import seedu.duke.messages.OperationMessages;
import seedu.duke.secrets.CryptoWallet;
import seedu.duke.storage.SecretMaster;
import seedu.duke.ui.Ui;

import java.util.HashSet;

/**
 * Represents the class to give a command to add a new Student ID to the SecureNUS system.
 */
public class AddCryptoWalletCommand extends AddSecretCommand {
    public static final String KEYWORD = "o/Crypto";
    private String username;
    private String privateKey;
    private String seedPhrase;

    /**
     * Constructs an AddStudentIDCommand object with the user input as the command parameter.
     *
     * @param input The command input entered by the user.
     */
    public AddCryptoWalletCommand(String input, HashSet<String> usedNames) throws IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException, OperationCancelException {
        super(input, usedNames, KEYWORD);
        username = inquire(InquiryMessages.USERNAME, "Username");
        privateKey = inquire(InquiryMessages.PRIVATE_KEY, "Private Key");
        seedPhrase = inquire(InquiryMessages.SEED_PHRASE, "Seed Phrase");
    }
    public AddCryptoWalletCommand(CryptoWallet cryptoWallet) {
        this.name = cryptoWallet.getName();
        this.folderName = cryptoWallet.getFolderName();
        this.username = cryptoWallet.getUsername();
        this.privateKey = cryptoWallet.getPrivateKey();
        this.seedPhrase = cryptoWallet.getSeedPhrase();
    }

    /**
     * Executes the AddStudentIDCommand to add a new Student ID to the SecureNUS system.
     *
     * @param secureNUSData
     */

    @Override
    public void execute(SecretMaster secureNUSData) throws ExceptionMain {
        assert secureNUSData != null;
        CryptoWallet cryptoWallet = new CryptoWallet(name, folderName, username, privateKey, seedPhrase);
        try {
            secureNUSData.addSecret(cryptoWallet);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (IllegalSecretNameException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (IllegalFolderNameException e) {
            throw new ExceptionMain(e.getMessage());
        }
        Ui.inform("I have added a new Student ID:\n" +
                "name       = " + name + "\n" +
                "Folder     = " + folderName + "\n" +
                "Username   = " + username + "\n" +
                "Private Key= " + HIDDEN_FIELD + "\n" +
                "Seed Phrase= " + HIDDEN_FIELD);

        Ui.inform(OperationMessages.SAVING);
        Backend.updateStorage(secureNUSData.listSecrets());
        Ui.inform(OperationMessages.SAVE_COMPLETE);
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false, as it is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
