package seedu.duke.storage;

import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.FolderNotEmptyException;
import seedu.duke.exceptions.secrets.FolderNotFoundException;
import seedu.duke.secrets.Secret;

import java.util.Hashtable;
import java.util.HashSet;

public class SecretSearcher {
    private final Hashtable<String, Secret> storage;

    // index using folder (FOR FUTURE FIND LIKE WITH FOLDER)
    private final Hashtable<String, Hashtable<String, Secret>> folders;

    public SecretSearcher(Hashtable<String, Secret> storage, Hashtable<String, Hashtable<String, Secret>> folders) {
        this.storage = storage;
        this.folders = folders;
    }

    public Secret get(String secretId) {
        return storage.get(secretId);
    }

    public void createFolder(String folderName) throws FolderExistsException {
        if (folders.containsKey(folderName)) {
            throw new FolderExistsException();
        }
        folders.put(folderName,
                new Hashtable<String, Secret>());
    }
    public void deleteFolder(String folderName) throws FolderNotFoundException, FolderNotEmptyException {
        if (!folders.containsKey(folderName)) {
            throw new FolderNotFoundException();
        }
        if (!folders.get(folderName).isEmpty()) {
            throw new FolderNotEmptyException();
        }
        folders.remove(folderName);
    }
    public void add(Secret secret) throws FolderExistsException {
        storage.put(secret.getUid(), secret);
        // creates a folder if it doesn't already exist (MIGHT remove in future)
        if (!folders.containsKey(secret.getFolderName())) {
            createFolder(secret.getFolderName());
        }
        folders.get(secret.getFolderName()).put(secret.getUid(), secret);
    }

    public void delete(Secret secret) {
        storage.remove(secret.getUid());
        folders.get(secret.getFolderName()).remove(secret.getUid());
        // delete folder if it is empty
        if (folders.get(secret.getFolderName()).isEmpty()) {
            folders.remove(secret.getFolderName());
        }
    }

    public HashSet getNames() {
        HashSet<String> nameHashset = new HashSet();
        for (String name : this.storage.keySet()) {
            nameHashset.add(name);
        }
        return nameHashset;
    }
}
