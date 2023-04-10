package seedu.securenus.command;

import org.junit.jupiter.api.Test;
import seedu.securenus.secrets.BasicPassword;

import static org.junit.jupiter.api.Assertions.assertFalse;

class AddSecretCommandTest {

    @Test
    void isExit() {
        AddSecretCommand addSecret = new AddBasicPasswordCommand(
                new BasicPassword("basicPassword1", "FolderName", "basicUsername", "Lorem Ipsum 112", "google.com"));
        assertFalse(addSecret.isExit());
    }
}
