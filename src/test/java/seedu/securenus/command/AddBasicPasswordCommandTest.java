package seedu.securenus.command;

import org.junit.jupiter.api.Test;
import seedu.securenus.secrets.BasicPassword;

import static org.junit.jupiter.api.Assertions.assertFalse;

class AddBasicPasswordCommandTest {

    @Test
    void isExit() {
        AddBasicPasswordCommand addBasicPassword = new AddBasicPasswordCommand(
                new BasicPassword("basicPassword1", "FolderName", "basicUsername", "Lorem Ipsum 112", "google.com"));
        assertFalse(addBasicPassword.isExit());
    }
}
