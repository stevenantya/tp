package seedu.duke;

import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.NUSNet;
import seedu.duke.secrets.StudentID;
import seedu.duke.storage.SecretEnumerator;
import seedu.duke.storage.SecretMaster;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretSearcher;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;



public class Backend {
    public static final String ENCRYPTION_IDENTIFIER = "DKENC";

    /**
     * Returns data from previous session as a SecretMaster Object.
     * If data is not available, a new file is created.
     *
     * @return SecretMaster
     */
    public static SecretMaster initialisation() {
        ArrayList<Secret> secretList = new ArrayList<Secret>();

        //create folder if it does not exist
        String pathOfCurrentDirectory = System.getProperty("user.dir");
        String assetsPath = java.nio.file.Paths.get(pathOfCurrentDirectory, "assets").toString();
        File assets = new File(assetsPath);
        if (!assets.exists()) {
            assets.mkdir();
        }
        //create file if it does not exist
        String databasePath = java.nio.file.Paths.get(assetsPath, "database.txt").toString();
        File database = new File(databasePath);
        try {
            if (!database.createNewFile()) {
                database.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            Scanner reader = new Scanner(database);
            while (reader.hasNextLine()) {
                String[] inputArray = reader.nextLine().split(",");
                secretList = Backend.readAndUpdate(inputArray, secretList);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        //for secretEnumerator
        Hashtable<String, ArrayList<Secret>> foldersHashTable =
            Backend.createFolderHashtable(secretList);
        SecretEnumerator secretEnumerator = new SecretEnumerator(secretList,
            foldersHashTable);
        //for secretSearcher
        Hashtable<String, Secret> nameHashtable = Backend.
            createNameHashtable(secretList);
        Hashtable<String, Hashtable<String, Secret>> hashtableFolders =
            Backend.createHashtableFolders(foldersHashTable);
        SecretSearcher secretSearcher = new SecretSearcher(nameHashtable, hashtableFolders);
        return new SecretMaster(secretSearcher, secretEnumerator);
    }

    /**
     * Returns ArrayList of Secret with the new Secret added.
     *
     * @param input String to create a Secret.
     * @param database Current ArrayList of Secret.
     * @return ArrayList of Secret
     */
    public static ArrayList<Secret> readAndUpdate(String[] input, ArrayList<Secret> database) {
        //create different password based on constructor
        //studentID
        if (input[0].equals("studentID")) {
            Secret secret = new StudentID(input[2], input[3], input[4]);
            database.add(secret);
        } else if (input[0].equals("NUSNetID")) {
            Secret secret = new NUSNet(input[2], input[3], input[4],
                    Backend.decode(input[5]));
            database.add(secret);
        } else if (input[0].equals("Password")) {
            Secret secret = new BasicPassword(input[2], input[3], Backend.decode(input[4]),
                    Backend.decode(input[5]), Backend.parseEmptyField(input[6]));
            database.add(secret);
        }
        //Password
        return database;
    }

    public static Hashtable<String, ArrayList<Secret>> createFolderHashtable(ArrayList<Secret> secretList) {
        Hashtable<String, ArrayList<Secret>> folderHashtable = new
            Hashtable<String, ArrayList<Secret>>();
        for (Secret secret : secretList) {
            String folderName = secret.getFolderName();
            ArrayList<Secret> secretsInFolder = new ArrayList<Secret>();
            if (folderHashtable.containsKey(folderName)) {
                secretsInFolder = folderHashtable.get(folderName);
            }
            secretsInFolder.add(secret);
            folderHashtable.put(folderName, secretsInFolder);
        }
        return folderHashtable;
    }
    public static Hashtable<String, Secret> createNameHashtable(ArrayList<Secret> secretList) {
        Hashtable<String, Secret> nameHashtable = new Hashtable<String, Secret>();
        for (Secret secret : secretList) {
            nameHashtable.put(secret.getName(), secret);
        }
        return nameHashtable;
    }
    public static Hashtable<String, Hashtable<String, Secret>> createHashtableFolders(
            Hashtable<String, ArrayList<Secret>> folderHashtable) {
        Hashtable<String, Hashtable<String, Secret>> hashtableFolders =
            new Hashtable<String, Hashtable<String, Secret>>();
        for (String folder : folderHashtable.keySet()) {
            hashtableFolders.put(folder, Backend.createNameHashtable(folderHashtable.get(folder)));
        }
        return hashtableFolders;
    }


    public static String encode(String field) {
        String encodedField = "";
        for (int i = 0; i < field.length(); i++) {
            int asciiValue = (int) (field.charAt(i) + 1);
            encodedField += (char) asciiValue;
        }
        return Backend.ENCRYPTION_IDENTIFIER + encodedField;
    }

    public static String decode(String field) {
        String modifiedField = field.substring(5);
        String actualField = "";
        for (int i = 0; i < modifiedField.length(); i++) {
            int asciiValue = (int) (modifiedField.charAt(i) - 1);
            actualField += (char) asciiValue;
        }
        return actualField;
    }

    public static String parseEmptyField(String field) {
        return field.equals("empty") ? "" : field;
    }

    /**
     * Saves changes made by the user during the session
     * in a .txt document.
     *
     * @param input the list of secrets provided by user.
     */
    public static void updateStorage(ArrayList<Secret> input) {
        String currDir = System.getProperty("user.dir");
        String databasePath = java.nio.file.Paths.get(currDir,
                "assets", "database.txt").toString();
        File database = new File(databasePath);

        try {
            FileWriter myWriter = new FileWriter(database);
            for (int i = 0; i < input.size(); i++) {
                Secret secret = input.get(i);
                myWriter.write(secret.toStringForDatabase() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
