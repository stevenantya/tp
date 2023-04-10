package seedu.securenus.storage;

import seedu.securenus.SecureNUSLogger;
import seedu.securenus.exceptions.secrets.InvalidCvcNumberException;
import seedu.securenus.ui.Ui;
import seedu.securenus.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.securenus.exceptions.secrets.InvalidExpiryDateException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.NonExistentFolderException;
import seedu.securenus.exceptions.secrets.SecretNotFoundException;

import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.secrets.CreditCard;
import seedu.securenus.secrets.CryptoWallet;
import seedu.securenus.secrets.NUSNet;
import seedu.securenus.secrets.Secret;
import seedu.securenus.secrets.StudentID;
import seedu.securenus.secrets.WifiPassword;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;

/**
 * Master class that manages the storage and retrieval of secrets and folders.
 */
public class SecretMaster {
    public static final String ALLOWED_NAMES_REGEX = "^[a-zA-Z0-9_]*$"; // only alphanumeric allowed
    public static final String DEFAULT_FOLDER = "unnamed";
    private SecretSearcher secretSearcher;
    private SecretEnumerator secretEnumerator;
    private HashSet<String> folders;
    private HashSet<String> secretNames;

    /**
     * Constructs a new SecretMaster object with an empty set of folders and secret names, and
     * Initializes the SecretSearcher and SecretEnumerator objects.
     */
    public SecretMaster() {
        secretSearcher = new SecretSearcher();
        secretEnumerator = new SecretEnumerator();
        folders = new HashSet<String>();
        secretNames = new HashSet<String>();
    }

    /**
     * Constructor for the SecretMaster class that takes in existing SecretSearcher and SecretEnumerator objects.
     *
     * @param secretSearcher SecretSearcher object containing existing secret data.
     * @param secretEnumerator SecretEnumerator object containing existing secret data.
     */
    public SecretMaster(SecretSearcher secretSearcher, SecretEnumerator secretEnumerator) {
        this.secretSearcher = secretSearcher;
        this.secretEnumerator = secretEnumerator;
        this.folders = secretEnumerator.getFolders();
        this.secretNames = secretSearcher.getNames();
    }

    /**
     * Checks if a given name is a legal name conforming to the standards.
     *
     * @param name String name to check.
     * @return boolean indicating whether the name is legal.
     */
    public static boolean isLegalFolderName(String name) {
        assert name != null;
        if (name.equals("")) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, folder name is empty, " + name);
        } else if (!name.matches(ALLOWED_NAMES_REGEX)) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, special characters in folder name, " + name);
        }
        return !name.equals("") && name.matches(ALLOWED_NAMES_REGEX);
    }

    /**
     * Retrieves the HashSet of folder names.
     *
     * @return HashSet of folder names.
     */
    public HashSet<String> getFolders() {
        assert this.folders != null;
        return folders;
    }

    /**
     * Retrieves the SecretSearcher object.
     *
     * @return SecretSearcher object.
     */
    public SecretSearcher getSecretSearcher() {
        assert this.secretSearcher != null;
        return secretSearcher;
    }

    /**
     * Retrieves the SecretEnumerator object.
     *
     *  @return SecretEnumerator object.
     */
    public SecretEnumerator getSecretEnumerator() {
        assert this.secretEnumerator != null;
        return secretEnumerator;
    }

    /**
     * Retrieves the HashSet of secret names.
     *
     * @return HashSet of secret names.
     */
    public HashSet<String> getSecretNames() {
        assert this.secretNames != null;
        return secretNames;
    }

    /**
     * Creates a new folder with the given name.
     *
     * @param folderName String name of the folder to be created.
     * @throws FolderExistsException if the folder already exists.
     * @throws IllegalFolderNameException if the folder name is not a legal name.
     */
    public void createFolder(String folderName) throws FolderExistsException, IllegalFolderNameException {
        assert this.folders != null;
        assert this.secretEnumerator != null;
        assert this.secretSearcher != null;
        assert folderName.length() > 0;
        if (!isLegalFolderName(folderName)) {
            throw new IllegalFolderNameException();
        }

        if (!folders.contains(folderName)) {
            folders.add(folderName);
            secretEnumerator.createFolder(folderName);
            secretSearcher.createFolder(folderName);
        }
    }

    /**
     * Retrieves the Secret object at the given index.
     *
     * @param index int index of the Secret object to retrieve.
     * @return Secret object at the given index.
     */
    public Secret getByIndex(int index) {
        assert index >= 0;
        assert this.secretEnumerator != null;
        return secretEnumerator.get(index);
    }

    /**
     * Retrieves the Secret object at the given index within the given folder.
     *
     * @param index int index of the Secret object to retrieve.
     * @param folder String name of the folder to retrieve the Secret from.
     * @return Secret object at the given index within the given folder.
     */
    public Secret getByIndex(int index, String folder) {
        assert index >= 0;
        assert folder.length() > 0;
        assert this.secretEnumerator != null;
        return secretEnumerator.get(index, folder);
    }

    /**
     * Retrieves an ArrayList of all Secrets.
     *
     * @return ArrayList of all Secrets.
     */
    public ArrayList<Secret> listSecrets() {
        assert this.secretEnumerator != null;
        return secretEnumerator.getList();
    }

    /**
     * Retrieves an ArrayList of all Secrets within the given folder.
     *
     * @param folderName String name of the folder to retrieve Secrets from.
     * @return ArrayList of all Secrets within the given folder.
     * @throws NonExistentFolderException if the folder does not exist.
     */
    public ArrayList<Secret> listSecrets(String folderName) throws NonExistentFolderException {
        assert folderName != null;
        assert folderName.length() >= 0;
        assert this.secretEnumerator != null;
        if (!folders.contains(folderName)) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, non existent folder, " + folderName);
            throw new NonExistentFolderException();
        }
        return secretEnumerator.getList(folderName);
    }

    /**
     * Retrieves the Secret object with the given name.
     *
     * @param secretName String name of the Secret object to retrieve.
     * @return Secret object with the given name.
     * @throws SecretNotFoundException if the Secret does not exist.
     */
    public Secret getByName(String secretName) throws SecretNotFoundException {
        assert secretName != null;
        assert secretName.length() > 0;
        assert this.secretNames != null;
        assert this.secretSearcher != null;
        if (!secretNames.contains(secretName)) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, non existent secret name, " + secretName);
            throw new SecretNotFoundException();
        }
        return secretSearcher.get(secretName);
    }

    /**
     * Adds a new Secret to the storage system.
     *
     * @param secret Secret object to be added.
     * @throws FolderExistsException if the folder specified in the Secret already exists.
     * @throws RepeatedIdException if the Secret has a repeated ID.
     * @throws IllegalSecretNameException if the Secret name is not a legal name.
     * @throws IllegalFolderNameException if the folder name in the Secret is not a legal name.
     */
    public void addSecret(Secret secret) throws FolderExistsException, RepeatedIdException,
            IllegalSecretNameException, IllegalFolderNameException {
        assert secret != null;
        assert this.secretNames != null;
        assert this.secretEnumerator != null;
        assert this.secretSearcher != null;
        if (!isLegalFolderName(secret.getName())) {
            throw new IllegalSecretNameException();
        }
        if (secretNames.contains(secret.getUid())) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, repeated ID, " + secret.getUid());
            throw new RepeatedIdException();
        }
        String folderName = secret.getFolderName();

        if (folderName != null) {
            createFolder(folderName);
        }
        secretNames.add(secret.getName());
        secretEnumerator.add(secret);
        secretSearcher.add(secret);
    }

    /**
     * Edits a secret by changing its name, folder name, and/or inquired fields.
     *
     * @param secret the secret to be edited
     * @param newName the new name of the secret (null to keep the old name)
     * @param newFolderName the new folder name of the secret (null to keep the old folder name)
     * @param inquiredFields an array of strings containing the new values for the inquired field
     *                       (null to keep the old values)
     * @throws FolderExistsException if the new folder name already exists
     */
    public void editSecret(Secret secret, String newName, String newFolderName,
                           String[] inquiredFields) throws FolderExistsException {

        assert secret != null;
        assert newName != null;
        assert newFolderName != null;
        assert inquiredFields != null; //must it be more than 0?
        assert newName.length() > 0;
        assert this.secretNames != null;
        assert this.secretEnumerator != null;
        assert this.secretSearcher != null;

        secretNames.remove(secret.getName());
        secretSearcher.delete(secret);
        secretEnumerator.delete(secret);

        if (newName != null) {
            secret.setName(newName);
        }
        if (newFolderName != null) {
            secret.setFolderName(newFolderName);
        }
        if (secret instanceof BasicPassword) {
            ((BasicPassword) secret).setUsername(inquiredFields[0]);
            ((BasicPassword) secret).setPassword(inquiredFields[1]);
            ((BasicPassword) secret).setUrl(inquiredFields[2]);
        } else if (secret instanceof CreditCard) {
            try {
                ((CreditCard) secret).setFullName(inquiredFields[0]);
                ((CreditCard) secret).setCreditCardNumber(inquiredFields[1]);
                ((CreditCard) secret).setCvcNumber(inquiredFields[2]);
                ((CreditCard) secret).setExpiryDate(inquiredFields[3]);
            } catch (InvalidCreditCardNumberException e) {
                SecureNUSLogger.LOGGER.log(Level.WARNING, "error, invalid credit card number, " + inquiredFields[1]);
                Ui.printError("Invalid Credit Card Number! Must be 16 digits long");
            } catch (InvalidExpiryDateException e) {
                SecureNUSLogger.LOGGER.log(Level.WARNING, "error, invalid expiry date, " + inquiredFields[3]);
                Ui.printError("Invalid Expiry Date! Must be in the format \"MM/YY\"");
            } catch (InvalidCvcNumberException e) {
                SecureNUSLogger.LOGGER.log(Level.WARNING, "error, invalid cvc number, " + inquiredFields[2]);
                Ui.printError("Invalid CVC Number! Must be in the correct format (e.g. 123):");
            }
        } else if (secret instanceof NUSNet) {
            ((NUSNet) secret).setNusNetId(inquiredFields[0]);
            ((NUSNet) secret).setPassword(inquiredFields[1]);
        } else if (secret instanceof StudentID) {
            ((StudentID) secret).setStudentID(inquiredFields[0]);
        } else if (secret instanceof WifiPassword) {
            ((WifiPassword) secret).setUsername(inquiredFields[0]);
            ((WifiPassword) secret).setPassword(inquiredFields[1]);
        } else if (secret instanceof CryptoWallet) {
            ((CryptoWallet) secret).setUsername(inquiredFields[0]);
            ((CryptoWallet) secret).setPrivateKey(inquiredFields[1]);
            ((CryptoWallet) secret).setSeedPhrase(inquiredFields[2]);
        }

        secretNames.add(secret.getName());
        secretSearcher.add(secret);
        secretEnumerator.add(secret);

    }

    /**
     * Removes a Secret from the storage system.
     *
     * @param secret Secret object to be removed.
     * @throws SecretNotFoundException if the Secret does not exist.
     */
    public void removeSecret(Secret secret) throws SecretNotFoundException {
        assert secret != null;
        assert this.secretNames != null;
        assert this.secretEnumerator != null;
        assert this.secretSearcher != null;

        if (!secretNames.contains(secret.getUid())) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, secret does not exists, " + secret.getUid());
            throw new SecretNotFoundException();
        }

        secretNames.remove(secret.getName());
        secretEnumerator.delete(secret);
        secretSearcher.delete(secret);
        String folderName = secret.getFolderName();

        if (!folderContainsSecrets(folderName)) {
            folders.remove(folderName);
        }
    }

    /**
     * This method checks if a folder with the specified name contains any secrets.
     *
     * @param folderName the name of the folder to be checked
     * @return true if the folder contains secrets, false otherwise
     */
    public boolean folderContainsSecrets(String folderName) {
        return secretEnumerator.folderExists(folderName);
    }
}
