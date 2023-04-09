package seedu.securenus.command;

import org.junit.jupiter.api.Test;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.secrets.InvalidExpiryDateException;
import seedu.securenus.secrets.CreditCard;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void inquireCreditCardNumber_retryThenSuccess() throws OperationCancelException {
        String input = "1234 1234 1234 123" + System.lineSeparator() + "1234 1234 1234 1234";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String expected = "1234 1234 1234 1234";
        Scanner scanner = new Scanner(System.in);
        String actual = addCreditCard.inquireCreditCardNumber(scanner);
        assertEquals(expected, actual);
    }

    @Test
    void inquireCvcNumber_retryThenSuccess() throws OperationCancelException {
        String input = "1234" + System.lineSeparator() + "123";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String expected = "123";
        Scanner scanner = new Scanner(System.in);
        String actual = addCreditCard.inquireCvcNumber(scanner);
        assertEquals(expected, actual);
    }

    @Test
    void inquireExpiryDate_retryThenSuccess() throws OperationCancelException {
        String input = "100/100" + System.lineSeparator() + "10/99";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String expected = "10/99";
        Scanner scanner = new Scanner(System.in);
        String actual = addCreditCard.inquireExpiryDate(scanner);
        assertEquals(expected, actual);
    }

    @Test
    void isExit() throws InvalidExpiryDateException, OperationCancelException {
        AddCreditCardCommand addCreditCard = new AddCreditCardCommand(
                new CreditCard("cc1", "FolderName","Tom James", "1234 1234 1234 1234", "123", "10/99"));
        assertFalse(addCreditCard.isExit());
    }
}
