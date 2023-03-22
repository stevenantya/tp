package seedu.duke.secrets;

import seedu.duke.Backend;
import seedu.duke.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.duke.exceptions.secrets.InvalidExpiryDateException;

/**
 * Main things to export
 * name: Str
 * folderName: Str
 * fullName: Str
 * creditCardNumber: Str
 * cvcNumber: int
 * expiryDate: Str
 *
 */
public class CreditCard extends Secret {
    private String fullName;
    private String creditCardNumber;
    private int cvcNumber;
    private String expiryDate;
    private static final String expiryDateFmt = "[0-1][0-9]/[0-3][0-9]";
    private static final String credictCardNumberFmt = "\\d{16}";
    public CreditCard(String name, String fullName,
                      String creditCardNumber,
                      int cvcNumber, String expiryDate) throws
            InvalidExpiryDateException, InvalidCreditCardNumberException {


        super(name);
        this.fullName = fullName;
        this.creditCardNumber = creditCardNumber;
        if (!creditCardNumber.matches(credictCardNumberFmt)) {
            throw new InvalidCreditCardNumberException();
        }
        this.cvcNumber = cvcNumber;
        if (!expiryDate.matches(expiryDateFmt)) {
            throw new InvalidExpiryDateException();
        }
        this.expiryDate = expiryDate;

    }

    public CreditCard(String name, String folderName,
                      String fullName, String creditCardNumber,
                      int cvcNumber, String expiryDate) throws InvalidExpiryDateException {
        super(name, folderName);
        this.fullName = fullName;
        this.creditCardNumber = creditCardNumber;
        this.cvcNumber = cvcNumber;
        if (!expiryDate.matches(expiryDateFmt)) {
            throw new InvalidExpiryDateException();
        }
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getCvcNumber() {
        return cvcNumber;
    }

    public void setCvcNumber(int cvcNumber) {
        this.cvcNumber = cvcNumber;
    }

    @Override
    public String getRevealStr() {
        return String.format("Credit Card Number: %s\n" +
                "CVC Number: %d\n" +
                "Expiry Date: %s", creditCardNumber, cvcNumber,
                expiryDate);
    }

    @Override
    public String toStringForDatabase() {
        String formattedString =  "CreditCard," + super.toStringForDatabase() +
                "," + this.fullName + "," + Backend.encode(this.creditCardNumber) + "," +
                Backend.encode("" + this.cvcNumber) + "," + this.expiryDate;
        return formattedString;
    }
}
