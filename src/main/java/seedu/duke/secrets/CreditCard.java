package seedu.duke.secrets;

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
 * TODO: Add way to check if creditCardNumber is correct
 */
public class CreditCard extends Secret {
    private String fullName;
    private String creditCardNumber;
    private int cvcNumber;
    private String expiryDate;
    private final String expiryDateFmt = "[0-1][0-9]/[0-2][0-9]";
    public CreditCard(String name, String fullName,
                      String creditCardNumber,
                      int cvcNumber, String expiryDate) throws InvalidExpiryDateException {

        super(name);
        this.fullName = fullName;
        this.creditCardNumber = creditCardNumber;
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
}
