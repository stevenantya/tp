package seedu.securenus.secrets;

import seedu.securenus.Backend;
import seedu.securenus.SecureNUSLogger;
import seedu.securenus.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.securenus.exceptions.secrets.InvalidCvcNumberException;
import seedu.securenus.exceptions.secrets.InvalidExpiryDateException;

import java.util.logging.Level;

/**
 * Represents a credit card entry in the user's secrets list.
 * Stores the credit card's name, full name, credit card number, CVC number and expiry date.
 * Credit card numbers are expected to be a string of 16 digits, while expiry dates are expected to be in the format
 * "MM/YY".
 */
public class CreditCard extends Secret {
    public static final String TYPE = "CreditCard";
    private static final String EXPIRY_DATE_FMT = "[0-1][0-9]/[0-9][0-9]";
    private static final String CREDIT_CARD_NUMBER_FMT = "\\d{4} \\d{4} \\d{4} \\d{4}";
    private static final String CVC_NUMBER_FMT = "\\d{3}";
    private String fullName;
    private String creditCardNumber;
    private String cvcNumber;
    private String expiryDate;

    /**
     * Constructor for a CreditCard object.
     *
     * @param name Name of the credit card.
     * @param fullName Full name of the credit card owner.
     * @param creditCardNumber 16-digit credit card number.
     * @param cvcNumber 3-digit CVC number of the credit card.
     * @param expiryDate Expiry date of the credit card in the format "MM/YY".
     * @throws InvalidExpiryDateException If the expiry date is not in the format "MM/YY".
     * @throws InvalidCreditCardNumberException If the credit card number is not a string of 16 digits.
     */
    public CreditCard(String name, String fullName,
                      String creditCardNumber,
                      String cvcNumber, String expiryDate) throws
            InvalidExpiryDateException, InvalidCreditCardNumberException {


        super(name);
        this.fullName = fullName;
        this.creditCardNumber = creditCardNumber;
        if (!creditCardNumber.matches(CREDIT_CARD_NUMBER_FMT)) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, credit card number format is illegal, " + expiryDate);
            throw new InvalidCreditCardNumberException();
        }
        this.cvcNumber = cvcNumber;
        if (!expiryDate.matches(EXPIRY_DATE_FMT)) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error,credit card expiry date format is illegal, " + expiryDate);
            throw new InvalidExpiryDateException();
        }
        this.expiryDate = expiryDate;

    }

    /**
     * Constructor for a CreditCard object in a specific folder.
     *
     * @param name Name of the credit card.
     * @param folderName Name of the folder containing the credit card.
     * @param fullName Full name of the credit card owner.
     * @param creditCardNumber 16-digit credit card number.
     * @param cvcNumber 3-digit CVC number of the credit card.
     * @param expiryDate Expiry date of the credit card in the format "MM/YY".
     * @throws InvalidExpiryDateException If the expiry date is not in the format "MM/YY".
     */
    public CreditCard(String name, String folderName,
                      String fullName, String creditCardNumber,
                      String cvcNumber, String expiryDate) throws InvalidExpiryDateException {
        super(name, folderName);
        this.fullName = fullName;
        this.creditCardNumber = creditCardNumber;
        this.cvcNumber = cvcNumber;
        if (!isLegalExpiryDate(expiryDate)) {
            throw new InvalidExpiryDateException();
        }
        this.expiryDate = expiryDate;
    }

    public static CreditCard addCreditCard(String name, String folderName, String
            fullName, String creditCardNumber, String cvcNumber, String expiryDate) throws
            InvalidExpiryDateException, InvalidCreditCardNumberException, InvalidCvcNumberException {

        if (!creditCardNumber.matches(CREDIT_CARD_NUMBER_FMT)) {
            throw new InvalidCreditCardNumberException();
        }
        if (!expiryDate.matches(EXPIRY_DATE_FMT)) {
            throw new InvalidExpiryDateException();
        }
        if (!isLegalCvcNumber(cvcNumber)) {
            throw new InvalidCvcNumberException();
        }
        return new CreditCard(name, folderName, fullName, creditCardNumber,
                cvcNumber, expiryDate);
    }

    public String getType() {
        return TYPE;
    }

    /**
     * Checks if the provided expiry date is legal.
     *
     * @param expiryDate the expiry date to be checked
     * @return true if the expiry date is legal, false otherwise
     * @throws NullPointerException if the expiry date is null
     */
    public static boolean isLegalExpiryDate(String expiryDate) {
        assert expiryDate != null;
        if (!expiryDate.matches(EXPIRY_DATE_FMT)) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error,credit card expiry date format is illegal, " + expiryDate);
            return false;
        }
        String[] monthAndYear = expiryDate.split("/");
        int month = Integer.parseInt(monthAndYear[0]);
        int year = Integer.parseInt(monthAndYear[1]);
        if (month < 1 || month > 12 || year < 1) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, credit card expiry date is illegal, " + expiryDate);
        }
        return month >= 1 && month <= 12 && year >= 1;
    }
    /**
     * Checks if the provided credit card number is legal.
     *
     * @param creditCardNumber the credit card number to be checked
     * @return true if the credit card number is legal, false otherwise
     * @throws NullPointerException if the credit card number is null
     */
    public static boolean isLegalCreditCardNumber(String creditCardNumber) {
        assert creditCardNumber != null;
        if (!creditCardNumber.matches(CREDIT_CARD_NUMBER_FMT)) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, credit card number is illegal, " + creditCardNumber);
        }
        return creditCardNumber.matches(CREDIT_CARD_NUMBER_FMT);
    }

    /**
     * Checks if the provided CVC number is legal.
     *
     * @param number the CVC number to be checked
     * @return true if the CVC number is legal, false otherwise
     * @throws NullPointerException if the CVC number is null
     */
    public static boolean isLegalCvcNumber(String number) {
        assert number != null;
        if (!number.matches(CVC_NUMBER_FMT)) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, cvc is illegal, " + number);
        }
        return number.matches(CVC_NUMBER_FMT);
    }

    /**
     * Returns the expiry date of the credit card.
     *
     * @return Expiry date of the credit card in the format "MM/YY".
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of the object.
     *
     * @param expiryDate the expiry date to be set
     * @throws InvalidExpiryDateException if the expiry date is invalid
     * @throws NullPointerException if the expiry date is null
     */
    public void setExpiryDate(String expiryDate) throws InvalidExpiryDateException {
        assert expiryDate != null;
        if (!isLegalExpiryDate(expiryDate)) {
            throw new InvalidExpiryDateException();
        }
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the full name associated with the credit card.
     *
     * @return the full name associated with the credit card
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the object.
     *
     * @param fullName the full name to be set
     * @throws NullPointerException if the full name is null
     */
    public void setFullName(String fullName) {
        assert fullName != null;
        this.fullName = fullName;
    }

    /**
     * Gets the credit card number.
     *
     * @return A String representing the credit card number.
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * Sets the credit card number of the object.
     *
     * @param creditCardNumber the credit card number to be set
     * @throws InvalidCreditCardNumberException if the credit card number is invalid
     * @throws NullPointerException if the credit card number is null
     */
    public void setCreditCardNumber(String creditCardNumber) throws InvalidCreditCardNumberException {
        assert creditCardNumber != null;
        if (!isLegalCreditCardNumber(creditCardNumber)) {
            throw new InvalidCreditCardNumberException();
        }
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * Returns the CVC number of the credit card.
     *
     * @return the CVC number
     */
    public String getCvcNumber() {
        return cvcNumber;
    }

    /**
     * Sets the CVC number of the object.
     *
     * @param cvcNumber the CVC number to be set
     * @throws InvalidCvcNumberException if the CVC number is invalid
     * @throws NullPointerException if the CVC number is null
     */
    public void setCvcNumber(String cvcNumber) throws InvalidCvcNumberException {
        assert cvcNumber != null;
        if (!isLegalCvcNumber(cvcNumber)) {
            throw new InvalidCvcNumberException();
        }
        this.cvcNumber = cvcNumber;
    }

    /**
     * Returns a string representation of the credit card, including its credit card number,
     * CVC number, and expiry date.
     *
     * @return a string representation of the credit card
     */
    @Override
    public String getRevealStr() {
        return String.format(
                "Name: %s\n" +
                "Full Name: %s\n" +
                "Credit Card Number: %s\n" +
                "CVC Number: %s\n" +
                "Expiry Date: %s", getName(), getFullName(), creditCardNumber, cvcNumber,
                expiryDate);
    }

    /**
     * Returns a string representation of the object that can be stored in the database.
     *
     * @return a string representation of the object that can be stored in the database
     */

    @Override
    public String toStringForDatabase() {
        String formattedString =  "CreditCard," + super.toStringForDatabase() +
                "," + Backend.encode(this.fullName) + "," + Backend.encode(this.creditCardNumber) + "," +
                Backend.encode("" + this.cvcNumber) + "," + this.expiryDate;
        return formattedString;
    }
}
