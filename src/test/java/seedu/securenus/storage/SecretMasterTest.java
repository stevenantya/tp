package seedu.securenus.storage;

import org.junit.jupiter.api.Test;
import seedu.securenus.exceptions.RepeatedIdException;

import seedu.securenus.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.InvalidExpiryDateException;
import seedu.securenus.exceptions.secrets.InvalidURLException;
import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.secrets.CreditCard;
import seedu.securenus.secrets.Secret;
import seedu.securenus.secrets.CryptoWallet;
import seedu.securenus.secrets.StudentID;
import seedu.securenus.secrets.WifiPassword;
import seedu.securenus.secrets.NUSNet;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * JUnit tests for the SecretMaster class in the storage package.
 */
class SecretMasterTest {

    /**
     * Tests the isLegalName method in the SecretMaster class.
     *
     * @throws IllegalFolderNameException if the folder name is illegal
     * @throws FolderExistsException if the folder already exists
     */
    @Test
    void isLegalName() throws IllegalFolderNameException, FolderExistsException {
        SecretMaster secretMaster = new SecretMaster();
        assertEquals(true, secretMaster.isLegalFolderName("gyujnuygvjkm"));
        assertEquals(true, secretMaster.isLegalFolderName("hfjewqsdierjdfhnreqwewqfvsvd"));
        assertEquals(true, secretMaster.isLegalFolderName("fvdwhjejsdkjfk879809"));
        assertEquals(false, secretMaster.isLegalFolderName("jhgfhdwv "));
        assertEquals(false, secretMaster.isLegalFolderName("jkfewrjfv90r93f47   "));
        assertEquals(false, secretMaster.isLegalFolderName("jkfewrjfv90r93f47^&IO(*&^"));
    }

    /**
     * Tests the createFolder method in the SecretMaster class.
     *
     * @throws IllegalFolderNameException if the folder name is illegal
     * @throws FolderExistsException if the folder already exists
     * @throws RepeatedIdException if the id of a secret is repeated
     * @throws IllegalSecretNameException if the secret name is illegal
     */
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

    /**
     * Tests the addSecret methods of the SecretMaster class for all secret types.
     * @throws FolderExistsException if a folder already exists with the same name
     * @throws InvalidExpiryDateException if an invalid expiry date is provided for a secret
     * @throws IllegalFolderNameException if an illegal folder name is used
     * @throws RepeatedIdException if a secret with the same ID already exists
     * @throws IllegalSecretNameException if an illegal secret name is used
     * @throws InvalidCreditCardNumberException if an invalid credit card number is provided for a credit card secret
     * @throws InvalidURLException if an invalid URL is provided for a basic password secret
     */
    @Test
    void addAllSecrets() throws FolderExistsException, InvalidExpiryDateException, IllegalFolderNameException,
            RepeatedIdException, IllegalSecretNameException, InvalidCreditCardNumberException,
            InvalidExpiryDateException, InvalidCreditCardNumberException, InvalidURLException {
        SecretMaster secretMaster = new SecretMaster();
        secretMaster.addSecret(new BasicPassword("basic1", "username1", "Password1",
                "http.com"));
        secretMaster.addSecret(new CreditCard("credit1", "HJ HJ UI",
                "1234 5678 9012 3456", "123",
                "12/23"));
        secretMaster.addSecret(new CryptoWallet("crypto1", "hjhbj",
                "fdertyuiytyui876ytfgyuit5rt",
                "hb jnjkm kjijh ijhui hjhb iujh uhbgv gfcd"));
        secretMaster.addSecret(new NUSNet("nusnet1", "folder1", "e0987654",
                "oitfghjmjh"));
        secretMaster.addSecret(new StudentID("hi", "T0987490A"));
        secretMaster.addSecret(new WifiPassword("wifi1", "username1", "password1"));
    }

    /**
     * Test for adding secrets with special characters in their names to SecretMaster.
     *
     * @throws InvalidURLException if an invalid URL is provided for a basic password secret
     * @throws IllegalFolderNameException if an illegal folder name is used
     * @throws RepeatedIdException if a secret with the same ID already exists
     * @throws IllegalSecretNameException if an illegal secret name is used
     * @throws FolderExistsException if a folder already exists with the same name
     */
    @Test
    void addSecretsWithCharacter() throws InvalidURLException, IllegalFolderNameException, RepeatedIdException,
            IllegalSecretNameException, FolderExistsException {
        SecretMaster secretMaster = new SecretMaster();
        secretMaster.addSecret(new BasicPassword("BasicPassword1", "username1", "Password1",
                "http.com"));
        secretMaster.addSecret(new BasicPassword("BasicPassword2_", "username1", "Password1",
                "http.com"));
        secretMaster.addSecret(new BasicPassword("BasicPassword2", "Folder9", "username1",
                "Password1",
                "http.com"));
        secretMaster.addSecret(new BasicPassword("BasicPassword4", "Folder9_", "username1",
                "Password1",
                "http.com"));
    }

    /**
     * Tests for editing a BasicPassword object.
     * @throws FolderExistsException if the folder already exists in the {@code SecretMaster}
     * @throws IllegalFolderNameException if the folder name is illegal
     * @throws RepeatedIdException if the secret ID already exists in the {@code SecretMaster}
     * @throws IllegalSecretNameException if the secret name is illegal
     * @throws InvalidURLException if the URL is not valid
     */
    @Test
    public void editBasicPassword() throws FolderExistsException, IllegalFolderNameException, RepeatedIdException,
            IllegalSecretNameException, InvalidURLException {
        SecretMaster secretMaster = new SecretMaster();
        BasicPassword secret = new BasicPassword("secret1", "folder1", "username1",
                "password1", "https://example.com");
        secretMaster.addSecret(secret);

        String newName = "secret2";
        String newFolderName = "folder2";
        String[] inquiredFields = {"newusername", "newpassword", "https://example.com"};
        secretMaster.editSecret(secret, newName, newFolderName, inquiredFields);

        assertEquals(newName, secret.getName());
        assertEquals(newFolderName, secret.getFolderName());
        assertEquals(inquiredFields[0], secret.getUsername());
        assertEquals(inquiredFields[1], secret.getPassword());
        assertEquals(inquiredFields[2], ((BasicPassword) secret).getUrl());
    }

    /**
     * Tests the {@code editSecret} method in the {@code SecretMaster} class to ensure that editing a credit card
     * secret
     * correctly updates its fields.
     *
     * @throws FolderExistsException if there is already a folder with the same name
     * @throws InvalidExpiryDateException if the expiry date provided is in an invalid format
     * @throws IllegalFolderNameException if the folder name provided is illegal
     * @throws RepeatedIdException if there is already a secret with the same name and folder name
     * @throws IllegalSecretNameException if the secret name provided is illegal
     */

    @Test
    public void editCreditCard() throws FolderExistsException, InvalidExpiryDateException,
            IllegalFolderNameException, RepeatedIdException, IllegalSecretNameException {
        SecretMaster secretMaster = new SecretMaster();
        CreditCard secret = new CreditCard("secret1", "folder1", "John Doe",
                "1234567890123456", "123", "12/23");
        secretMaster.addSecret(secret);

        String newName = "secret2";
        String newFolderName = "folder2";
        String[] inquiredFields = {"Jane Doe", "1234 5678 9012 3456", "456", "12/24"};
        secretMaster.editSecret(secret, newName, newFolderName, inquiredFields);

        assertEquals(newName, secret.getName());
        assertEquals(newFolderName, secret.getFolderName());
        assertEquals(inquiredFields[0], ((CreditCard) secret).getFullName());
        assertEquals(inquiredFields[1], ((CreditCard) secret).getCreditCardNumber());
        assertEquals(inquiredFields[2], ((CreditCard) secret).getCvcNumber());
        assertEquals(inquiredFields[3], ((CreditCard) secret).getExpiryDate());
    }

    /**
     * Tests for the {@code editSecret()} method in {@code SecretMaster} class for {@code NUSNet} secrets.
     * Tests if the method can successfully edit the details of a {@code NUSNet} secret.
     *
     * @throws FolderExistsException If the folder already exists in the storage.
     * @throws IllegalFolderNameException If the folder name contains illegal characters.
     * @throws RepeatedIdException If there is already a secret with the same name and folder name.
     * @throws IllegalSecretNameException If the secret name contains illegal characters.
     */
    @Test
    public void editNUSNet() throws FolderExistsException, IllegalFolderNameException,
            RepeatedIdException, IllegalSecretNameException {
        SecretMaster secretMaster = new SecretMaster();
        NUSNet secret = new NUSNet("secret1", "folder1", "e0123456", "password1");
        secretMaster.addSecret(secret);

        String newName = "secret2";
        String newFolderName = "folder2";
        String[] inquiredFields = {"e0012345", "password2"};
        secretMaster.editSecret(secret, newName, newFolderName, inquiredFields);

        assertEquals(newName, secret.getName());
        assertEquals(newFolderName, secret.getFolderName());
        assertEquals(inquiredFields[0], ((NUSNet) secret).getNusNetId());
        assertEquals(inquiredFields[1], ((NUSNet) secret).getPassword());
    }

    /**
     * Tests the {@code editSecret()} method of {@code SecretMaster} class when editing a {@code StudentID} object.
     *
     * @throws FolderExistsException if there is a duplicate folder name
     * @throws IllegalFolderNameException if the folder name is invalid
     * @throws RepeatedIdException if there is a duplicate ID for a secret
     * @throws IllegalSecretNameException if the secret name is invalid
     */
    @Test
    public void editStudentID() throws FolderExistsException, IllegalFolderNameException,
            RepeatedIdException, IllegalSecretNameException {
        SecretMaster secretMaster = new SecretMaster();
        StudentID secret = new StudentID("secret1", "folder1", "e0123456");
        secretMaster.addSecret(secret);

        String newName = "secret2";
        String newFolderName = "folder2";
        String[] inquiredFields = {"e0012345"};
        secretMaster.editSecret(secret, newName, newFolderName, inquiredFields);

        assertEquals(newName, secret.getName());
        assertEquals(newFolderName, secret.getFolderName());
    }
}
