package seedu.securenus.command;

import seedu.securenus.Backend;
import seedu.securenus.SecureNUSLogger;
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
import java.util.logging.Level;

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
     * Constructs a command for adding a credit card entry to a password manager.
     *
     * @param input the user input string that triggered this command
     * @param usedNames a set of names that have already been used in the password manager
     * @throws IllegalFolderNameException if the name of the folder is illegal
     * @throws IllegalSecretNameException if the name of the password entry is illegal
     * @throws RepeatedIdException if the password manager already contains an entry with the same ID
     * @throws OperationCancelException if the user cancels the operation
     */
    public AddCreditCardCommand(String input, HashSet<String> usedNames) throws IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException, OperationCancelException {
        super(input, usedNames, KEYWORD);
        fullName = inquire(InquiryMessages.FULLNAME, "Full Name");
        creditCardNumber = inquireCreditCardNumber();
        cvcNumber = inquireCvcNumber();
        expiryDate = inquireExpiryDate();
    }

    public AddCreditCardCommand(CreditCard creditCard) throws OperationCancelException {
        super(creditCard);
        fullName = creditCard.getFullName();
        creditCardNumber = creditCard.getCreditCardNumber();
        cvcNumber = creditCard.getCvcNumber();
        expiryDate = creditCard.getExpiryDate();
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
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, invalid expiry date, " + expiryDate);
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
            throw new RuntimeException();
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

        Backend.updateStorage(secureNUSData.listSecrets());
    }

    /**
     * Prompts the user to enter their credit card number.
     *
     * @return the valid credit card number entered by the user.
     * @throws OperationCancelException if the user cancels the operation.
     */
    public String inquireCreditCardNumber() throws OperationCancelException {
        String creditCardNumber = inquire(InquiryMessages.CREDIT_CARD_NUMBER, "Credit Card Number");;
        while(!CreditCard.isLegalCreditCardNumber(creditCardNumber)) {
            System.out.println(InquiryMessages.CREDIT_CARD_NUMBER_RETRY);
            creditCardNumber = inquire(InquiryMessages.CREDIT_CARD_NUMBER, "Credit Card Number");
        }
        return creditCardNumber;
    }

    /*
     * Prompts the user to enter their CVC number.
     *
     * @return the valid CVC number entered by the user.
     * @throws OperationCancelException if the user cancels the operation.
     */
    public String inquireCvcNumber() throws OperationCancelException {
        String number = inquire(InquiryMessages.CVC_NUMBER, "CVC Number");
        while(!CreditCard.isLegalCvcNumber(number)) {
            System.out.println(InquiryMessages.CVC_NUMBER_RETRY);
            number = inquire(InquiryMessages.CVC_NUMBER, "CVC Number");
        }
        return number;
    }

    /**
     * Prompts the user to enter the expiry date of their credit card.
     *
     * @return the valid expiry date entered by the user
     * @throws OperationCancelException if the user cancels the operation
     */
    public String inquireExpiryDate() throws OperationCancelException {
        String number = inquire(InquiryMessages.EXPIRY_DATE, "Expiry Date");
        while(!CreditCard.isLegalExpiryDate(number)) {
            System.out.println(InquiryMessages.EXPIRY_DATE_RETRY);
            number = inquire(InquiryMessages.EXPIRY_DATE, "Expiry Date");
        }
        return number;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
