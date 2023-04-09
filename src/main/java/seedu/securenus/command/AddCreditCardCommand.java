package seedu.securenus.command;

import seedu.securenus.ui.Ui;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.InvalidExpiryDateException;
import seedu.securenus.messages.ErrorMessages;
import seedu.securenus.messages.InquiryMessages;
import seedu.securenus.secrets.CreditCard;
import seedu.securenus.storage.SecretMaster;

import java.util.HashSet;

/**
 * A class to add a new credit card to the user's secureNUSData.
 * Inherits from AddSecretCommand, which contains the common attributes
 * and methods needed to add a secret to the user's secureNUSData.
 */
public class AddCreditCardCommand extends AddSecretCommand {

    public static final String KEYWORD = "o/CreditCard";
    private String fullName;
    private String creditCardNumber;
    private String cvcNumber;
    private String expiryDate;

    /**
     * Constructor for AddCreditCardCommand class.
     * Extracts the necessary information from the input string using the super constructor
     * and prompts the user for additional information.
     *
     * @param input User input command string.
     */
    public AddCreditCardCommand(String input, HashSet<String> usedNames) throws IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException, OperationCancelException {
        super(input, usedNames, KEYWORD);
        fullName = inquire(InquiryMessages.FULLNAME, "Full Name");
        creditCardNumber = inquireCreditCardNumber();
        cvcNumber = inquireCvcNumber();
        expiryDate = inquireExpiryDate();
    }

    /**
     * Adds a new credit card to the user's secureNUSData.
     *
     * @param secureNUSData The user's secureNUSData object.
     * @throws RuntimeException if there is a repeated id or if there is an issue with the name or folder name.
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        assert secureNUSData != null;
        CreditCard creditCard = null;
        try {
            creditCard = new CreditCard(name,folderName,fullName, creditCardNumber, cvcNumber, expiryDate);
        } catch (InvalidExpiryDateException e) {
            Ui.printError(ErrorMessages.INVALID_EXPIRY_DATE);
            return;
        }
        assert creditCard != null;
        try {
            secureNUSData.addSecret(creditCard);
        } catch (RepeatedIdException e) {
            Ui.printError(ErrorMessages.INVALID_EXPIRY_DATE);
        } catch (FolderExistsException e) {
            Ui.printError(ErrorMessages.FOLDER_EXISTS);
            throw new RuntimeException(); // remove and use logging instead
        } catch (IllegalSecretNameException e) {
            Ui.printError(ErrorMessages.ILLEGAL_SECRET_NAME);
        } catch (IllegalFolderNameException e) {
            Ui.printError(ErrorMessages.ILLEGAL_FOLDER_NAME);
        }
        Ui.inform("I have added a new Credit Card:\n" +
                "Name           = " + name + "\n" +
                "Folder         = " + folderName + "\n" +
                "Full Name      = " + fullName + "\n" +
                "Credit Card No = " + HIDDEN_FIELD + "\n" +
                "CVC No         = " + HIDDEN_FIELD + "\n" +
                "Expiry Date    = " + HIDDEN_FIELD);
    }

    public String inquireCreditCardNumber() throws OperationCancelException {
        String creditCardNumber = inquire(InquiryMessages.CREDIT_CARD_NUMBER, "Credit Card Number");;
        while(!CreditCard.isLegalCreditCardNumber(creditCardNumber)) {
            System.out.println(InquiryMessages.CREDIT_CARD_NUMBER_RETRY);
            creditCardNumber = inquire(InquiryMessages.CREDIT_CARD_NUMBER, "Credit Card Number");
        }
        return creditCardNumber;
    }

    public String inquireCvcNumber() throws OperationCancelException {
        String number = inquire(InquiryMessages.CVC_NUMBER, "CVC Number");
        while(!CreditCard.isLegalCvcNumber(number)) {
            System.out.println(InquiryMessages.CVC_NUMBER_RETRY);
            number = inquire(InquiryMessages.CVC_NUMBER, "CVC Number");
        }
        return number;
    }

    public String inquireExpiryDate() throws OperationCancelException {
        String number = inquire(InquiryMessages.EXPIRY_DATE, "Expiry Date");
        while(!CreditCard.isLegalExpiryDate(number)) {
            System.out.println(InquiryMessages.EXPIRY_DATE_RETRY);
            number = inquire(InquiryMessages.EXPIRY_DATE, "Expiry Date");
        }
        return number;
    }
}
