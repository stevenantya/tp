package seedu.securenus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;

import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.InvalidExpiryDateException;
import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.secrets.CreditCard;
import seedu.securenus.secrets.CryptoWallet;
import seedu.securenus.secrets.NUSNet;
import seedu.securenus.secrets.StudentID;
import seedu.securenus.secrets.WifiPassword;
import seedu.securenus.storage.SecretEnumerator;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretSearcher;
import seedu.securenus.ui.Ui;

/**
 * Class which represents the backend of the SecureNUS application.
 * Handles file input/output and secret creation and manipulation.
 */
public class Backend {
    public static boolean isCorrupted = false;
    public static boolean isDatabaseEmpty = true;
    private static final int DECRYPTION_STARTING_INDEX = 5;
    private static final String DATABASE_FOLDER = "assets";
    private static final String DATABASE_FILE = "database.txt";
    private static final String DELIMITER = ",";
    private static final String ENCRYPTION_IDENTIFIER = "DKENC";
    private static final String EMPTY_FIELD_IDENTIFIER = "empty";
    private static final String USER_DIRECTORY_IDENTIFIER = "user.dir";
    private static final String PASSWORD_IDENTIFIER = "Password";
    private static final String CREDIT_CARD_IDENTIFIER = "CreditCard";
    private static final String CRYPTOWALLET_IDENTIFIER = "CryptoWallet";
    private static final String NUSNETID_IDENTIFIER = "nusNetId";
    private static final String STUDENTID_IDENTIFIER = "studentID";
    private static final String WIFI_PASSWORD_IDENTIFIER = "wifiPassword";


    /**
     * Returns data from previous session as a SecretMaster Object.
     * If data is not available, a new file is created.
     *
     * @return SecretMaster
     */
    public static SecretMaster initialisation() {
        SecretMaster secretMaster = Backend.initialiseSecretMaster();

        File database = Backend.createAssetFolderAndDatabaseFile();
        String databasePath = Backend.getDatabasePath();
        try {
            if (!database.createNewFile()) {
                database.createNewFile();
            }
            //read history if database file exists
            try {
                BufferedReader reader = new BufferedReader(new FileReader(databasePath));
                String input = reader.readLine();
                while (input != null && input.length() > 0) {
                    String[] inputArray = input.split(Backend.DELIMITER);
                    secretMaster = Backend.readAndUpdate(inputArray, secretMaster);
                    String data = secretMaster.getByIndex(
                            secretMaster.getSecretEnumerator().size() - 1).toStringForDatabase();
                    boolean isCorrupted = !Backend.hash(data).equals(inputArray[inputArray.length - 1]);
                    if (isCorrupted) {
                        SecureNUSLogger.LOGGER.log(Level.WARNING, "command, Backend Initialisation");
                        SecureNUSLogger.LOGGER.log(Level.WARNING, "error, parameters altered but still valid, " + data);
                        throw new IOException();
                    }
                    input = reader.readLine();
                }
                reader.close();
            } catch (Exception e) {
                Backend.isCorrupted = true;
                secretMaster = Backend.initialiseSecretMaster();
            }
        } catch (IOException e) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "command, Backend Initialisation");
            SecureNUSLogger.LOGGER.log(Level.SEVERE, "fatal, database cannot be created");
            Ui.inform("Database cannot be initialised! User data will not be saved");
            secretMaster = Backend.initialiseSecretMaster();
        }

        if (secretMaster.listSecrets().size() != 0) {
            isDatabaseEmpty = false;
        }
        return secretMaster;
    }

    /**
     * Creates an asset folder to store the database file if it does not already exist,
     * and returns the File object for the database file.
     *
     * @return The File object for the database file.
     */
    public static File createAssetFolderAndDatabaseFile() {
        //locate assets folder / try to create assets folder if it does not exist
        String pathOfCurrentDirectory = System.getProperty(Backend.USER_DIRECTORY_IDENTIFIER);
        String assetsPath = Paths.get(pathOfCurrentDirectory, Backend.DATABASE_FOLDER).toString();
        File assets = new File(assetsPath);
        if (!assets.exists()) {
            assets.mkdir();
        }

        //locate database file / try to create database file if it does not exist
        String databasePath = Paths.get(assetsPath, Backend.DATABASE_FILE).toString();
        File database = new File(databasePath);
        return database;
    }

    /**
     * Returns the full file path of the database file.
     *
     * @return A string representing the full file path of the database file.
     */
    public static String getDatabasePath() {
        String pathOfCurrentDirectory = System.getProperty(Backend.USER_DIRECTORY_IDENTIFIER);
        String databasePath = Paths.get(pathOfCurrentDirectory, Backend.DATABASE_FOLDER,
                Backend.DATABASE_FILE).toString();
        return databasePath;
    }

    /**
     * Initializes a new SecretMaster object with an empty ArrayList of secrets, a SecretEnumerator and a
     * SecretSearcher.
     * The SecretEnumerator and SecretSearcher objects are populated with the appropriate data structures, using the
     * secretList and foldersHashTable objects.
     *
     * @return a new SecretMaster object with the populated SecretEnumerator and SecretSearcher objects.
     */
    public static SecretMaster initialiseSecretMaster() {
        ArrayList<Secret> secretList = new ArrayList<Secret>();

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
     * @param input    String to create a Secret.
     * @param secretMaster Current secretMaster
     * @return SecretMaster
     */
    public static SecretMaster readAndUpdate(String[] input, SecretMaster secretMaster) throws
            FolderExistsException, RepeatedIdException, IllegalSecretNameException,
            IllegalFolderNameException, InvalidExpiryDateException, IOException {

        if (Secret.isIllegalName(input[2]) || !SecretMaster.isLegalFolderName(input[3])) { //1st filter - name & folder
            SecureNUSLogger.LOGGER.log(Level.WARNING, "command, Backend Initialisation");
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, secret name/folder altered, " +
                input[2] + " " + input[3]);
            throw new IOException();
        }
        if (input[0].equals(Backend.PASSWORD_IDENTIFIER)) { //no 2nd filter
            Secret secret = new BasicPassword(input[2], input[3], Backend.decode(input[4]),
                    Backend.decode(input[5]), Backend.decode(input[6]));
            secretMaster.addSecret(secret);
        } else if (input[0].equals(Backend.CREDIT_CARD_IDENTIFIER)) {
            if (CreditCard.isLegalCreditCardNumber(Backend.decode(input[5])) && CreditCard.isLegalCvcNumber(
                    Backend.decode(input[6])) && CreditCard.isLegalExpiryDate(input[7])) { //2nd filter
                Secret secret = new CreditCard(input[2], input[3], Backend.decode(input[4]),
                        Backend.decode(input[5]), Backend.decode(input[6]), input[7]);
                secretMaster.addSecret(secret);
            } else {
                SecureNUSLogger.LOGGER.log(Level.WARNING, "command, Backend Initialisation");
                SecureNUSLogger.LOGGER.log(Level.WARNING, "error, credit card number/cvc/expiry date altered, " +
                        Backend.decode(input[5]) + " " + Backend.decode(input[6]) + Backend.decode(input[7]));
            }
        } else if (input[0].equals(Backend.CRYPTOWALLET_IDENTIFIER)) {
            Secret secret = new CryptoWallet(input[2], input[3], Backend.decode(input[4]), Backend.decode(input[5]),
                Backend.decode(input[6]), new ArrayList<String>());
            secretMaster.addSecret(secret);
        } else if (input[0].equals(Backend.NUSNETID_IDENTIFIER)) {
            if (NUSNet.isLegalId(input[4])) { //2nd filter
                Secret secret = new NUSNet(input[2], input[3], input[4], Backend.decode(input[5]));
                secretMaster.addSecret(secret);
            } else {
                SecureNUSLogger.LOGGER.log(Level.WARNING, "command, Backend Initialisation");
                SecureNUSLogger.LOGGER.log(Level.WARNING, "error, NUSNet id altered, " + input[4]);
                throw new IOException();
            }
        } else if (input[0].equals(Backend.STUDENTID_IDENTIFIER)) {
            if (StudentID.isLegalId(input[4])) { //2nd filter
                Secret secret = new StudentID(input[2], input[3], input[4]);
                secretMaster.addSecret(secret);
            } else {
                SecureNUSLogger.LOGGER.log(Level.WARNING, "command, Backend Initialisation");
                SecureNUSLogger.LOGGER.log(Level.WARNING, "error, Student ID altered, " + input[4]);
                throw new IOException();
            }
        } else if (input[0].equals(Backend.WIFI_PASSWORD_IDENTIFIER)) {
            Secret secret = new WifiPassword(input[2], input[3], Backend.decode(input[4]),
                    Backend.decode(input[5]));
            secretMaster.addSecret(secret);
        } else {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "command, Backend Initialisation");
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, field identifier altered, " + input[0]);
            throw new IOException();
        }
        return secretMaster;
    }

    /**
     * Creates and returns a hashtable of all the secrets grouped by folder.
     *
     * @param secretList the list of secrets to group by folder.
     * @return Hashtable of secrets grouped by folder.
     */
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

    /**
     * Creates and returns a hashtable of all the secrets by their name.
     *
     * @param secretList the list of secrets to group by name.
     * @return Hashtable of secrets grouped by name.
     */
    public static Hashtable<String, Secret> createNameHashtable(ArrayList<Secret> secretList) {
        Hashtable<String, Secret> nameHashtable = new Hashtable<String, Secret>();
        for (Secret secret : secretList) {
            nameHashtable.put(secret.getName(), secret);
        }
        return nameHashtable;
    }

    /**
     * Creates and returns a hashtable of all the secrets grouped by folder and name.
     *
     * @param folderHashtable the hashtable of secrets grouped by folder.
     * @return Hashtable of secrets grouped by folder and name.
     */
    public static Hashtable<String, Hashtable<String, Secret>> createHashtableFolders(
            Hashtable<String, ArrayList<Secret>> folderHashtable) {
        Hashtable<String, Hashtable<String, Secret>> hashtableFolders =
                new Hashtable<String, Hashtable<String, Secret>>();
        for (String folder : folderHashtable.keySet()) {
            hashtableFolders.put(folder, Backend.createNameHashtable(folderHashtable.get(folder)));
        }
        return hashtableFolders;
    }

    /**
     * Encodes a given field using a custom encryption method.
     *
     * @param field the field to be encoded.
     * @return the encoded field.
     */
    public static String encode(String field) {
        String encodedField = "";
        for (int i = 0; i < field.length(); i++) {
            int asciiValue = (int) (field.charAt(i) + 1);
            if (field.charAt(i) == '+') {
                encodedField += "PLUS";
            } else if (field.charAt(i) == ',') {
                encodedField += "COMMA";
            } else {
                encodedField += (char) asciiValue;
            }
        }
        if (field.equals("")) {
            encodedField += "empty";
        }
        return Backend.ENCRYPTION_IDENTIFIER + encodedField;
    }

    /**
     * Decodes a given field that was encoded using a custom encryption method.
     *
     * @param field the field to be decoded.
     * @return the decoded field.
     */
    public static String decode(String field) {
        String modifiedField = field.substring(Backend.DECRYPTION_STARTING_INDEX);
        String actualField = "";
        int i = 0;
        while (i < modifiedField.length() ) {
            int asciiValue = (int) (modifiedField.charAt(i) - 1);
            Character character = modifiedField.charAt(i);
            if (character.equals('P')
                    && (i + 4 <= modifiedField.length()) &&
                    modifiedField.substring(i, i+4).equals("PLUS")) {
                actualField += '+';
                i = i + 4;
            } else if (character.equals('C') && i + 5 <= modifiedField.length() &&
                    modifiedField.substring(i, i+5).equals("COMMA")) {
                actualField += ',';
                i = i + 5;
            } else {
                actualField += (char) asciiValue;
                i = i + 1;
            }
        }
        return actualField;
    }

    /**
     * Parses an empty field to return an empty string if it is "empty".
     *
     * @param field the field to be parsed.
     * @return an empty string if field is "empty", else returns the field.
     */
    public static String parseEmptyField(String field) {
        return field.equals(Backend.EMPTY_FIELD_IDENTIFIER) ? "" : field;
    }

    public static String hash(String data) {
        int hashcode = 0;
        for (int i = 0; i < data.length(); i++) {
            hashcode += (int) (data.charAt(i));
        }
        return "" + hashcode;
    }

    /**
     * Saves changes made by the user during the session
     * in a .txt document.
     *
     * @param input the list of secrets provided by user.
     */
    public static void updateStorage(ArrayList<Secret> input) {

        File database = Backend.createAssetFolderAndDatabaseFile();
        try {
            FileWriter myWriter = new FileWriter(database);
            for (int i = 0; i < input.size(); i++) {
                Secret secret = input.get(i);

                myWriter.write(secret.toStringForDatabase() + "," +
                        Backend.hash(secret.toStringForDatabase())+ "\n");
            }
            myWriter.close();
            SecureNUSLogger.LOGGER.log(Level.INFO, "data successfully written to datbase");
        } catch (IOException e) {
            Ui.inform("Database is not initialised! All user data will not be saved");
            SecureNUSLogger.LOGGER.log(Level.SEVERE, "fatal, database removed or not initialised");
        }
    }
}
