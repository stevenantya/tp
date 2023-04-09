package seedu.securenus.command;

import org.junit.jupiter.api.Test;
import seedu.securenus.secrets.CryptoWallet;

import static org.junit.jupiter.api.Assertions.assertFalse;

class AddCryptoWalletCommandTest {

    @Test
    void isExit() {
        AddCryptoWalletCommand addCryptoWallet = new AddCryptoWalletCommand(new CryptoWallet("cw1",
                "FolderName", "test_user",
                "test_private_key", "test_seed_phrase"));
        assertFalse(addCryptoWallet.isExit());
    }
}
