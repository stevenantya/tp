package seedu.duke.storage;

import seedu.duke.exceptions.FolderExistsException;
import seedu.duke.exceptions.FolderNotEmptyException;
import seedu.duke.exceptions.FolderNotFoundException;
import seedu.duke.secrets.Secret;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class SecretEnumerator {
    private final ArrayList<Secret> storage;

    // index using folder (FOR FUTURE FIND LIKE WITH FOLDER)
    private final Hashtable<String, ArrayList<Secret>> folders;

    public SecretEnumerator(ArrayList<Secret> storage, Hashtable<String, ArrayList<Secret>> folders) {
        this.storage = storage;
        this.folders = folders;
    }

    public void createFolder(String folderName) throws FolderExistsException {
        if (folders.containsKey(folderName)) {
            throw new FolderExistsException();
        }
        folders.put(folderName,
                new ArrayList<Secret>());
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

    public Secret get(int index) {
        if (index >= storage.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return storage.get(index);
    }

    public Secret get(int index, String folderName) {
        // assumes folder already exists
        ArrayList<Secret> tempStorage = folders.get(folderName);

        if (index >= tempStorage.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return tempStorage.get(index);
    }

    public ArrayList<Secret> getList() {
        return storage;
    }

    public ArrayList<Secret> getList(String folderName) {
        return folders.get(folderName);
    }

    public void add(Secret secret) throws FolderExistsException {
        storage.add(secret);
        // creates a folder if it doesn't already exist
        if (!folders.containsKey(secret.getFolderName())) {
            createFolder(secret.getFolderName());
        }
        folders.get(secret.getFolderName()).add(secret);
    }

    public void delete(Secret secret) {
        storage.remove(secret);
        folders.get(secret.getFolderName()).remove(secret);
        // delete folder if it is empty
        if (folders.get(secret.getFolderName()).isEmpty()) {
            folders.remove(secret.getFolderName());
        }
    }

    public int size() {
        return this.storage.size();
    }

    public HashSet getFolders() {
        HashSet<String> folderHashset = new HashSet();
        for (String name : this.folders.keySet()) {
            folderHashset.add(name);
        }
        return folderHashset;
    }
}
