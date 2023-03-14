package seedu.duke.secrets;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.secrets.InvalidExpiryDateException;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    @Test
    void noFolderCreationTest () throws InvalidExpiryDateException {
        CreditCard creditCard = new CreditCard("creditCard1",
                "John Doe Lim Guang", "12345678909876543", 123,
                "12/23");
        assertEquals("unnamed", creditCard.getFolderName().toString());
        assertEquals("John Doe Lim Guang", creditCard.getFullName());
        assertEquals("12345678909876543", creditCard.getCreditCardNumber());
        assertEquals("creditCard1", creditCard.getUid());
        assertEquals("creditCard1", creditCard.getName());
        assertEquals("12/23", creditCard.getExpiryDate());
    }

    void withFolderCreationTest () throws InvalidExpiryDateException {
        CreditCard creditCard = new CreditCard("creditCard1", "folder1",
                "John Doe Lim Guang", "12345678909876543", 123,
                "12/23");
        assertEquals("folder1", creditCard.getFolderName());
        assertEquals("John Doe Lim Guang", creditCard.getFullName());
        assertEquals("12345678909876543", creditCard.getCreditCardNumber());
        assertEquals("creditCard1", creditCard.getUid());
        assertEquals("creditCard1", creditCard.getName());
        assertEquals("12/23", creditCard.getExpiryDate());
    }

    void LegalDatesTest() throws InvalidExpiryDateException {
        assertThrows(InvalidExpiryDateException.class, () -> {
            new CreditCard("creditCard1", "folder1",
                    "John Doe Lim Guang", "12345678909876543", 123,
                    "22/23");
            }
        );
        assertThrows(InvalidExpiryDateException.class, () -> {
                    new CreditCard("creditCard1", "folder1",
                            "John Doe Lim Guang", "12345678909876543", 123,
                            "12/43");
                }
        );
        assertThrows(InvalidExpiryDateException.class, () -> {
                    new CreditCard("creditCard1", "folder1",
                            "John Doe Lim Guang", "12345678909876543", 123,
                            "123/23");
                }
        );
        assertThrows(InvalidExpiryDateException.class, () -> {
                    new CreditCard("creditCard1", "folder1",
                            "John Doe Lim Guang", "12345678909876543", 123,
                            "12/53");
                }
        );
        assertThrows(InvalidExpiryDateException.class, () -> {
                    new CreditCard("creditCard1", "folder1",
                            "John Doe Lim Guang", "12345678909876543", 123,
                            "12/g");
                }
        );
        assertThrows(InvalidExpiryDateException.class, () -> {
                    new CreditCard("creditCard1", "folder1",
                            "John Doe Lim Guang", "12345678909876543", 123,
                            "kfc");
                }
        );
        assertThrows(InvalidExpiryDateException.class, () -> {
                    new CreditCard("creditCard1", "folder1",
                            "John Doe Lim Guang", "12345678909876543", 123,
                            "@#/$%");
                }
        );
    }
}