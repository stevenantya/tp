package seedu.securenus.command;

import org.junit.jupiter.api.Test;
import seedu.securenus.secrets.WifiPassword;

import static org.junit.jupiter.api.Assertions.assertFalse;

class AddWifiPasswordCommandTest {

    @Test
    void isExit() {
        AddWifiPasswordCommand addWifiPassword = new AddWifiPasswordCommand(
                new WifiPassword("Name", "UserName", "Password"));
        assertFalse(addWifiPassword.isExit());
    }
}
