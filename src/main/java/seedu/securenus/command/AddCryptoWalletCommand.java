package seedu.securenus.command;

import seedu.securenus.Backend;
import seedu.securenus.exceptions.ExceptionMain;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.messages.InquiryMessages;
import seedu.securenus.secrets.CryptoWallet;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

import java.util.HashSet;

/**
 * Represents the command to add a new Crypto wallet to the user's SecureNUS data.
 * Inherits from the AddSecretCommand, which contains the common attributes
 * and methods needed to add a secret to the user's SecureNUS data.
 */
public class AddCryptoWalletCommand extends AddSecretCommand {
    public static final String KEYWORD = "o/Crypto";
    private String username;
    private String privateKey;
    private String seedPhrase;

    /**
     * Constructs a command for adding a cryptocurrency wallet entry to a password manager.
     *
     * @param input the user input string that triggered this command
     * @param usedNames a set of names that have already been used in the password manager
     * @throws IllegalFolderNameException if the name of the folder is illegal
     * @throws IllegalSecretNameException if the name of the password entry is illegal
     * @throws RepeatedIdException if the password manager already contains an entry with the same ID
     * @throws OperationCancelException if the user cancels the operation
     */
    public AddCryptoWalletCommand(String input, HashSet<String> usedNames) throws IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException, OperationCancelException {
        super(input, usedNames, KEYWORD);
        username = inquire(InquiryMessages.USERNAME, "Username");
        privateKey = inquire(InquiryMessages.PRIVATE_KEY, "Private Key");
        seedPhrase = inquire(InquiryMessages.SEED_PHRASE, "Seed Phrase");
    }

    /**
     * Constructor for AddCryptoWalletCommand class.
     * Takes in a CryptoWallet object and extracts the necessary attributes needed to create a new CryptoWallet.
     *
     * @param cryptoWallet The CryptoWallet object containing the necessary information to create a new CryptoWallet.
     */
    public AddCryptoWalletCommand(CryptoWallet cryptoWallet) {
        this.name = cryptoWallet.getName();
        this.folderName = cryptoWallet.getFolderName();
        this.username = cryptoWallet.getUsername();
        this.privateKey = cryptoWallet.getPrivateKey();
        this.seedPhrase = cryptoWallet.getSeedPhrase();
    }

    /**
     * Executes the command to add a cryptocurrency wallet entry to a password manager.
     *
     * @param secureNUSData the password manager to which the entry should be added
     * @throws ExceptionMain if there is an issue adding the entry to the password manager
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

        Backend.updateStorage(secureNUSData.listSecrets());
    }
}
