package seedu.duke.command;

import seedu.duke.exceptions.OperationCancelException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.messages.InquiryMessages;
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
import seedu.duke.ui.Ui;

import java.util.HashSet;

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

    /**
     * Constructs an EditCommand object with the specified input string.
     * Extracts the name of the secret to edit and the new values of the fields to be updated from the input string.
     *
     * @param input the input string that specifies which secret to edit and the new values of the fields to be updated.
     */
    public EditCommand(String input, HashSet<String> usedNames) throws IllegalSecretNameException,
            IllegalFolderNameException, SecretNotFoundException {
        this.name = extractName(input);
        if (Secret.isIllegalName(name)) {
            throw new IllegalSecretNameException();
        } else if (!usedNames.contains(name)) {
            throw new SecretNotFoundException();
        }
    }

    public String extractName(String input) {
        return super.extractName(input, "edit");
    }

    /**
     * Asks user for the new fields that will replace the old fields.
     *
     * @return The new fields
     */
    public String[] inquireFields(Secret secret) throws OperationCancelException {
        String[] inquiredFields = null;
        if (secret instanceof BasicPassword) {
            inquiredFields = new String[3];
            inquiredFields[0] = inquire(InquiryMessages.USERNAME_EDIT, "Username");
            inquiredFields[1] = inquire(InquiryMessages.PASSWORD_EDIT, "Password");
            inquiredFields[2] = inquire(InquiryMessages.URL_EDIT, "URL");
        } else if (secret instanceof CreditCard) {
            inquiredFields = new String[4];
            inquiredFields[0] = inquire(InquiryMessages.FULLNAME_EDIT, "Full Name");
            inquiredFields[1] = inquireCreditCardNumber();
            inquiredFields[2] = inquireCvcNumber();
            inquiredFields[3] = inquireExpiryDate();
        } else if (secret instanceof NUSNet) {
            inquiredFields = new String[2];
            inquiredFields[0] = inquireNusNetId();
            inquiredFields[1] = inquire(InquiryMessages.PASSWORD_EDIT, "Password");
        } else if (secret instanceof StudentID) {
            inquiredFields = new String[1];
            inquiredFields[0] = inquireStudentID();
        } else if (secret instanceof WifiPassword) {
            inquiredFields = new String[2];
            inquiredFields[0] = inquire(InquiryMessages.USERNAME_EDIT, "Username");
            inquiredFields[1] = inquire(InquiryMessages.PASSWORD_EDIT, "Password");
        } else if (secret instanceof CryptoWallet) {
            inquiredFields = new String[3];
            inquiredFields[0] = inquire(InquiryMessages.USERNAME_EDIT, "Username");
            inquiredFields[1] = inquire(InquiryMessages.PRIVATE_KEY_EDIT, "Private Key");
            inquiredFields[2] = inquire(InquiryMessages.SEED_PHRASE_EDIT, "Seed Phrase");
        }
        return inquiredFields;
    }

    // FOR QUICKER INQUIRY
    public String inquireCreditCardNumber() throws OperationCancelException {
        String creditCardNumber = inquire(InquiryMessages.CREDIT_CARD_NUMBER_EDIT, "Credit Card Number");
        while(!CreditCard.isLegalCreditCardNumber(creditCardNumber)) {
            System.out.println(InquiryMessages.CREDIT_CARD_NUMBER_RETRY);
            creditCardNumber = inquire(InquiryMessages.CREDIT_CARD_NUMBER_EDIT, "Credit Card Number");
        }
        return creditCardNumber;
    }


    public String inquireCvcNumber() throws OperationCancelException {
        String number = inquire(InquiryMessages.CVC_NUMBER_EDIT, "CVC Number");
        while(!CreditCard.isLegalCvcNumber(number)) {
            System.out.println(InquiryMessages.CVC_NUMBER_RETRY);
            number = inquire(InquiryMessages.CVC_NUMBER_EDIT, "CVC Number");
        }
        return number;
    }

    public String inquireExpiryDate() throws OperationCancelException {
        String number = inquire(InquiryMessages.EXPIRY_DATE_EDIT, "Expiry Date");
        while(!CreditCard.isLegalExpiryDate(number)) {
            System.out.println(InquiryMessages.EXPIRY_DATE_RETRY);
            number = inquire(InquiryMessages.EXPIRY_DATE_EDIT, "Expiry Date");
        }
        return number;
    }

    public String inquireNusNetId() throws OperationCancelException {
        String nusNetId = inquire(InquiryMessages.NUSNET_ID_EDIT, "NUSNet ID");
        while (!NUSNet.isLegalId(nusNetId)) {
            nusNetId = inquire(InquiryMessages.NUSNET_ID_RETRY, "NUSNet ID");
        }
        return nusNetId;
    }

    public String inquireStudentID() throws OperationCancelException {
        String studentID = inquire(InquiryMessages.STUDENT_ID_EDIT, "Student ID");
        while (!StudentID.isLegalId(studentID)) {
            studentID = inquire(InquiryMessages.STUDENT_ID_RETRY, "Student ID");
        }
        return studentID;
    }

    /**
     * Edits the specified secret in the SecretMaster object with the new values of the fields.
     *
     * @param secureNUSData the SecretMaster object containing the list of secrets.
     */
    @Override
    public void execute(SecretMaster secureNUSData) throws SecretNotFoundException, OperationCancelException, 
            FolderExistsException {
        Secret passwordSecret;
        passwordSecret = secureNUSData.getByName(name);
        String[] inquiredFields = inquireFields(passwordSecret);
        secureNUSData.editSecret(passwordSecret, passwordSecret.getName(), passwordSecret.getFolderName(),
                inquiredFields);
        Ui.inform("Secret has been edited! Check it out using the 'list' function");
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
