package seedu.duke.storage;

import seedu.duke.exceptions.FolderExistsException;
import seedu.duke.exceptions.NonExistentFolderException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.SecretNotFoundException;
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
    private final SecretSearcher secretSearcher = new SecretSearcher(); // Hash Table
    // use for listing secrets based on order it was added in
    private final SecretEnumerator secretEnumerator= new SecretEnumerator(); // Array view
    // to ensure folders and passwords are distinct
    private HashSet<String> folders = new HashSet<String>();
    private HashSet<String> secretNames = new HashSet<String>();

    public SecretMaster() {
        this.folders = new HashSet<>();
        this.secretNames = new HashSet<>();

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

    public Secret getByName(String secretName) {
        return secretSearcher.get(secretName);
    }

    public void addSecret(Secret secret) throws FolderExistsException, RepeatedIdException {
        if (secretNames.contains(secret.getUid())) {
            throw new RepeatedIdException();
        }
        String folderName = secret.getFolderName();
        // Creating a new folder
        if (!folders.contains(folderName)) {
            // Assumes folder doesnt already exist
            folders.add(folderName);
            secretEnumerator.createFolder(folderName);
            secretSearcher.createFolder(folderName);
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
}
