package seedu.securenus.storage;

import org.junit.jupiter.api.Test;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.securenus.exceptions.secrets.InvalidExpiryDateException;
import seedu.securenus.exceptions.secrets.InvalidURLException;
import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.secrets.CreditCard;
import seedu.securenus.secrets.CryptoWallet;
import seedu.securenus.secrets.StudentID;
import seedu.securenus.secrets.WifiPassword;
import seedu.securenus.secrets.NUSNet;

/**
 * JUnit Test for the SecretSearcher() Class
 */
class SecretSearcherTest {
    /**
     * Tests the add() method of the SecretSearcher class, specifically to check if all types of secrets can be added
     * without exceptions being thrown.
     *
     * @throws FolderExistsException if the folder already exists in the SecretMaster
     * @throws InvalidExpiryDateException if the credit card expiry date format is invalid
     * @throws InvalidCreditCardNumberException if the credit card number is not in the correct format
     * @throws InvalidURLException if the URL is invalid
     */
    @Test
    void addAllPasswords() throws FolderExistsException, InvalidExpiryDateException, InvalidCreditCardNumberException,
            InvalidURLException {
        SecretSearcher secretSearcher = new SecretSearcher();
        secretSearcher.add(new BasicPassword("basic1", "username1", "Password1",
                "http.com"));
        secretSearcher.add(new CreditCard("credit1", "HJ HJ UI", "1234 5678 9012 3456",
                "123",
                "12/23"));
        secretSearcher.add(new CryptoWallet("crypto1", "hjhbj", "fdertyuiytyui876ytfgyuit5rt",
                "hb jnjkm kjijh ijhui hjhb iujh uhbgv gfcd"));
        secretSearcher.add(new NUSNet("nusnet1", "folder1", "e0987654",
                "oitfghjmjh"));
        secretSearcher.add(new StudentID("hi", "T0987490A"));
        secretSearcher.add(new WifiPassword("wifi1", "username1", "password1"));
    }
}
