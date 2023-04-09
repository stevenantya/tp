package seedu.securenus.messages;

/**
 * Contains error messages for various exceptions and errors.
 */
public class ErrorMessages {
    public static final String INVALID_COMMAND = "Invalid Command: Type 'menu' command to" +
            " see the list of usable commands";
    public static final String INVALID_EXPIRY_DATE = "Invalid Expiry Date: Provide something of the format: MM/YY";
    public static final String REPEATED_ID = "RepeatedId: This name has been used before. Use a different name";
    public static final String ILLEGAL_FOLDER_NAME =
            "Illegal Folder Name: Please use only alphanumeric numbers and '_'";
    public static final String ILLEGAL_SECRET_NAME =
            "Illegal Secret Name: Please use only alphanumeric numbers and '_'";
    public static final String FOLDER_EXISTS = "Folder Exists: This should not happen, please contact administrator " +
            "if you see this";
    public static final String INSUFFICIENT_PARAMS = "Insufficient Parameters: Add the required parameters for this " +
            "command!\nPlease use the 'menu' command if you are uncertain";
    public static final String INVALID_FIELD = "The input field {o/Field} does not exist! Try one that exists " +
            "from our menu.";

    public static final String SECRET_NOT_FOUND = "The input Secret does not exist";
    public static final String FOLDER_NOT_FOUND = "The input Folder does not exist";
    public static final String NULL_SECRET = "No Secret provided. Make sure to include the name of the secret you " +
            "want to do this operation on";
    public static final String NULL_FOLDER = "Folder provided is Null input. Make sure to include the name of the " +
            "folder you want to do this operation on";
}
