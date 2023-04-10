package seedu.securenus.command;

import org.junit.jupiter.api.Test;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.secrets.InvalidExpiryDateException;
import seedu.securenus.secrets.CreditCard;

import static org.junit.jupiter.api.Assertions.assertFalse;

class AddCreditCardCommandTest {
    private final AddCreditCardCommand addCreditCard;
    {
        try {
            CreditCard creditCard =
                    new CreditCard("cc1", "FolderName", "Tom James", "1234 1234 1234 1234", "123", "10/99");
            addCreditCard = new AddCreditCardCommand(creditCard);
        } catch (InvalidExpiryDateException | OperationCancelException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void isExit() throws InvalidExpiryDateException, OperationCancelException {
        AddCreditCardCommand addCreditCard = new AddCreditCardCommand(
                new CreditCard("cc1", "FolderName","Tom James", "1234 1234 1234 1234", "123", "10/99"));
        assertFalse(addCreditCard.isExit());
    }
}
