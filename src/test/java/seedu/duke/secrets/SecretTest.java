package seedu.duke.secrets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecretTest {

    @Test
    void isIllegalName_checkWeirdChars() {
        String[] names = {"$", "#", "!", "@", "%", "^",
            "&", "*", "(", ")", "~", "`", "-", "+", "="};
        for (String name: names) {
            assertEquals(true, Secret.isIllegalName(name));
        }
        assertEquals(false, Secret.isIllegalName("Garena Login"));
    }

    @Test
    void getUidAndName() {
        Secret secret = new Secret("name", "folder");
        assertEquals("name", secret.getUid());
        assertEquals("name", secret.getName());
    }

    @Test
    void getFolder() {
        Secret secret = new Secret("name", "folder");
        assertEquals("folder", secret.getFolderName());
    }

    @Test
    void editName() {
        Secret secret = new Secret("name", "folder");
        secret.editName("newName");
        assertEquals("newName", secret.getUid());
        assertEquals("newName", secret.getName());
        assertEquals("folder", secret.getFolderName());
    }

    @Test
    void setFolderName() {
        Secret secret = new Secret("name", "folder");
        secret.setFolderName("newFolder");
        assertEquals("name", secret.getUid());
        assertEquals("name", secret.getName());
        assertEquals("newFolder", secret.getFolderName());
    }
}
