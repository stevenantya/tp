package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.InvalidExpiryDateException;
import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.CreditCard;
import seedu.duke.secrets.Secret;
import seedu.duke.secrets.CryptoWallet;
import seedu.duke.secrets.StudentID;
import seedu.duke.secrets.WifiPassword;
import seedu.duke.secrets.NUSNet;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SecretMasterTest {

    @Test
    void isLegalName() throws IllegalFolderNameException, FolderExistsException {
        SecretMaster secretMaster = new SecretMaster();
        assertEquals(true, secretMaster.isLegalName("gyujnuygvjkm"));
        assertEquals(true, secretMaster.isLegalName("hfjewqsdierjdfhnreqwewqfvsvd"));
        assertEquals(true, secretMaster.isLegalName("fvdwhjejsdkjfk879809"));
        assertEquals(false, secretMaster.isLegalName("jhgfhdwv "));
        assertEquals(false, secretMaster.isLegalName("jkfewrjfv90r93f47   "));
        assertEquals(false, secretMaster.isLegalName("jkfewrjfv90r93f47^&IO(*&^"));
    }

    @Test
    void createFolder() throws IllegalFolderNameException, FolderExistsException, RepeatedIdException,
            IllegalSecretNameException, IllegalFolderNameException, IllegalSecretNameException, FolderExistsException {
        SecretMaster secretMaster = new SecretMaster();
        HashSet<String> set = new HashSet<String>();
        set.add("unnamed");
        assertEquals(new HashSet<String>(), secretMaster.getFolders());
        assertThrows(IllegalFolderNameException.class, () -> {
            secretMaster.createFolder("\"jkfewrjfv90r93f47^&IO(*&^");
        });
        assertThrows(IllegalFolderNameException.class, () -> {
            secretMaster.createFolder("jhfe ");
        });
        assertEquals(new HashSet<String>(), secretMaster.getFolders());
        Secret secret1 = new Secret("blimp");
        secretMaster.addSecret(secret1);
        assertEquals(set, secretMaster.getFolders());
        Secret secret2 = new Secret("blimp", "folder1");
        Secret secret3 = new Secret("blimp2", "folder1");
        set.add(secret2.getFolderName());
        System.out.println(set);
        assertNotEquals(set, secretMaster.getFolders());
        assertThrows(RepeatedIdException.class, () -> {
            secretMaster.addSecret(secret2);
        });
        secretMaster.addSecret(secret3);
        assertEquals(set, secretMaster.getFolders());
    }

    @Test
    void addAllSecrets() throws FolderExistsException, InvalidExpiryDateException, IllegalFolderNameException,
            RepeatedIdException, IllegalSecretNameException, InvalidCreditCardNumberException,
            InvalidExpiryDateException, InvalidCreditCardNumberException {
        SecretMaster secretMaster = new SecretMaster();
        secretMaster.addSecret(new BasicPassword("basic1", "username1", "Password1",
                "http.com"));
        secretMaster.addSecret(new CreditCard("credit1", "HJ HJ UI", "1234567890123456", 123,
                "12/23"));
        secretMaster.addSecret(new CryptoWallet("crypto1", "hjhbj", "fdertyuiytyui876ytfgyuit5rt",
                "hb jnjkm kjijh ijhui hjhb iujh uhbgv gfcd"));
        secretMaster.addSecret(new NUSNet("nusnet1", "folder1", "e0987654", "oitfghjmjh"));
        secretMaster.addSecret(new StudentID("hi", "T0987490A"));
        secretMaster.addSecret(new WifiPassword("wifi1", "username1", "password1"));
    }
}
