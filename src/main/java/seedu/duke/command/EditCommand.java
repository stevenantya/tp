package seedu.duke.command;

import seedu.duke.ui.Ui;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;

import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.CreditCard;
import seedu.duke.secrets.CryptoWallet;
import seedu.duke.secrets.NUSNet;
import seedu.duke.secrets.Secret;
import seedu.duke.secrets.StudentID;
import seedu.duke.secrets.WifiPassword;
import seedu.duke.storage.SecretMaster;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Represents a class to give a command to edit a secret in the secureNUS application.
 * The EditCommand class extends the Command class.
 * The EditCommand class takes in a String input that specifies which secret to edit
 * and the new values of the fields to be updated.
 * The EditCommand class extracts the new values of the fields from the input string using regular expressions.
 * The class then uses these values to edit the secret in the SecretMaster object.
 */
public class EditCommand extends Command {
    private final String name;
    private final String newFolderName;
    private final String newName;

    /**
     * Constructs an EditCommand object with the specified input string.
     * Extracts the name of the secret to edit and the new values of the fields to be updated from the input string.
     *
     * @param input the input string that specifies which secret to edit and the new values of the fields to be updated.
     */
    public EditCommand(String input) {
        String[] extractedFields = extract(input);
        this.name = extractedFields[0];
        this.newFolderName = extractedFields[1];
        this.newName = extractedFields[2];
    }

    /**
     * Asks user for the new fields that will replace the old fields.
     *
     * @return The new fields
     */
    public String[] inquireFields(Secret secret) {
        String[] inquiredFields = null;

        if (secret instanceof BasicPassword) {
            inquiredFields = new String[3];
            System.out.println("Enter the new Username: ");
            inquiredFields[0] = Ui.readLine();
            System.out.println("Enter the new Password: ");
            inquiredFields[1] = Ui.readLine();
            System.out.println("Enter the new URL: ");
            inquiredFields[2] = Ui.readLine();
        } else if (secret instanceof CreditCard) {
            inquiredFields = new String[4];
            System.out.println("Enter the new Full Name: ");
            inquiredFields[0] = Ui.readLine();
            System.out.println("Enter the new Credit Card Number: ");
            inquiredFields[1] = Ui.readLine();
            System.out.println("Enter the new CVC Number: ");
            inquiredFields[2] = Ui.readLine();
            System.out.println("Enter the new Expiry Date: ");
            inquiredFields[3] = Ui.readLine();
        } else if (secret instanceof NUSNet) {
            inquiredFields = new String[2];
            System.out.println("Enter the new NUSNet ID: ");
            inquiredFields[0] = Ui.readCommand();
            System.out.println("Enter the new Credit Card Number: ");
            inquiredFields[1] = Ui.readCommand();
        } else if (secret instanceof StudentID) {
            inquiredFields = new String[1];
            System.out.println("Enter the new Student ID: ");
            inquiredFields[0] = Ui.readCommand();
        } else if (secret instanceof WifiPassword) {
            inquiredFields = new String[2];
            System.out.println("Enter the new Username: ");
            inquiredFields[0] = Ui.readCommand();
            System.out.println("Enter the new Password: ");
            inquiredFields[1] = Ui.readCommand();
        } else if (secret instanceof CryptoWallet) {
            inquiredFields = new String[3];
            System.out.println("Enter the new Username: ");
            inquiredFields[0] = Ui.readCommand();
            System.out.println("Enter the new Private Key: ");
            inquiredFields[1] = Ui.readCommand();
            System.out.println("Enter the new Seed Phrase: ");
            inquiredFields[2] = Ui.readCommand();
        }

        return inquiredFields;
    }

    /**
     * Extracts the name of the secret to edit and the new values of the fields to be updated from the input string
     * using regular expressions.
     *
     * @param input the input string that specifies which secret to edit and the new values of the fields to be updated.
     * @return an array of Strings containing the extracted values of the fields to be updated.
     */
    public String[] extract(String input) {
        String[] extractedFields = new String[3];

        // Define regular expression patterns
        final Pattern namePattern = Pattern.compile("p/([\\w\\s]+)");
        final Pattern newFolderPattern = Pattern.compile("-f nf/([\\w\\s]+)");
        final Pattern newNamePattern = Pattern.compile("-N np/([\\w\\s]+)");  // rmb to check for valid Name

        // Extract values using regular expressions
        Matcher nameMatcher = namePattern.matcher(input);
        Matcher newFolderMatcher = newFolderPattern.matcher(input);
        Matcher newNameMatcher = newNamePattern.matcher(input);

        // Check if there is a match and extract the value
        extractedFields[0] = nameMatcher.find() ? nameMatcher.group(1).trim() : null;
        extractedFields[1] = newFolderMatcher.find() ? newFolderMatcher.group(1).trim() : null;
        extractedFields[2] = newNameMatcher.find() ? newNameMatcher.group(1).trim() : null;

        return extractedFields;
    }

    /**
     * Edits the specified secret in the SecretMaster object with the new values of the fields.
     *
     * @param secureNUSData the SecretMaster object containing the list of secrets.
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        Secret passwordSecret;
        try {
            passwordSecret = secureNUSData.getByName(name);
            String[] inquiredFields = inquireFields(passwordSecret);
            secureNUSData.editSecret(passwordSecret, newName, newFolderName, inquiredFields);
        } catch (SecretNotFoundException e) {
            Ui.printError("(Make sure you follow this format: \"edit p/PASSWORD_NAME\")");
        } catch (FolderExistsException e) {
            Ui.printError("(That folder already exists)");
        }
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
