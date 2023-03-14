package seedu.duke.storage;

import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.NonExistentFolderException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.secrets.Secret;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The master class for storing secrets
 * It handles all of the difficult work in the backend
 * regarding storage of secrets, etc
 */
public class SecretMaster {
    // use for quick finding
    private static final String DEFAULT_FOLDER = "unnamed";
    private static final String ALLOWED_NAMES_REGEX = "^[a-zA-Z0-9_]*$"; // only alphanumeric allowed
    private SecretSearcher secretSearcher; // Hash Table
    // use for listing secrets based on order it was added in
    private SecretEnumerator secretEnumerator; // Array view
    // to ensure folders and passwords are distinct
    private HashSet<String> folders;
    private HashSet<String> secretNames;

    public SecretMaster(SecretSearcher secretSearcher, SecretEnumerator secretEnumerator) {
        this.secretSearcher = secretSearcher;
        this.secretEnumerator = secretEnumerator;
        this.folders = secretEnumerator.getFolders();
        this.secretNames = secretSearcher.getNames();
    }

    public boolean isLegalName(String name) {
        return name.matches(ALLOWED_NAMES_REGEX);
    }

    public HashSet<String> getFolders() {
        return folders;
    }

    public SecretSearcher getSecretSearcher() {
        return secretSearcher;
    }

    public SecretEnumerator getSecretEnumerator() {
        return secretEnumerator;
    }

    public HashSet<String> getSecretNames() {
        return secretNames;
    }

    public void createFolder(String folderName) throws FolderExistsException, IllegalFolderNameException {

        if (!isLegalName(folderName)) {
            throw new IllegalFolderNameException();
        }

        if (!folders.contains(folderName)) {
            // Assumes folder doesnt already exist
            folders.add(folderName);
            secretEnumerator.createFolder(folderName);
            secretSearcher.createFolder(folderName);
        }

    public Secret getByIndex(int index) {
        return secretEnumerator.get(index);
    }

    public Secret getByIndex(int index, String folder) {
        return secretEnumerator.get(index, folder);
    }

    public ArrayList<Secret> listSecrets() {
        return secretEnumerator.getList();
    }

    public ArrayList<Secret> listSecrets(String folderName) throws NonExistentFolderException {
        if (!folders.contains(folderName)) {
            throw new NonExistentFolderException();
        }
        return secretEnumerator.getList(folderName);
    }

    public Secret getByName(String secretName) throws SecretNotFoundException {
        if (!secretNames.contains(secretName)) {
            throw new SecretNotFoundException();
        }
        return secretSearcher.get(secretName);
    }

    public void addSecret(Secret secret) throws FolderExistsException, RepeatedIdException,
            IllegalSecretNameException, IllegalFolderNameException {
        if (!isLegalName(secret.getName())) {
            throw new IllegalSecretNameException();
        }
        if (secretNames.contains(secret.getUid())) {
            throw new RepeatedIdException();
        }
        String folderName = secret.getFolderName();
        // Creating a new folder

        if (folderName != null) {
            createFolder(folderName);
        }
        secretNames.add(secret.getName());
        secretEnumerator.add(secret);
        secretSearcher.add(secret);
    }


    public void removeSecret(Secret secret) throws SecretNotFoundException {
        if (!secretNames.contains(secret.getUid())) {
            throw new SecretNotFoundException();
        }
        String folderName = secret.getFolderName();
        secretNames.remove(secret.getName());
        secretEnumerator.delete(secret);
        secretSearcher.delete(secret);
        // Deleting a folder if it is empty now done automatically
        //    if (!folders.contains(folderName)) {
        //        // Assumes folder doesnt already exist
        //        folders.add(folderName);
        //        secretEnumerator.deleteFolder(folderName);
        //        secretSearcher.deleteFolder(folderName);
        //    }
    }

    public ArrayList<String> listSecretNames() {
        ArrayList<String> secretNamesList = new ArrayList<String>();
        for (Secret secret : secretEnumerator.getList()) {
            secretNamesList.add(secret.getName());
        }
        return secretNamesList;
    }

    public ArrayList <String> listSecretNames(String folderName) throws NonExistentFolderException {
        if (!folders.contains(folderName)) {
            throw new NonExistentFolderException();
        }
        ArrayList<String> secretNamesList = new ArrayList<String>();
        for (Secret secret : secretEnumerator.getList(folderName)) {
            secretNamesList.add(secret.getName());
        }
        return secretNamesList;
    }
}
