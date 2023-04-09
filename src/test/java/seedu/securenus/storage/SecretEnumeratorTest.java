package seedu.securenus.storage;

import org.junit.jupiter.api.Test;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.secrets.Secret;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
/**
 * JUnit test class for SecretEnumerator class.
 * This class tests the behavior of the class when the list of secrets and the hashtable of secrets are empty.
 * Specifically, it tests the getter methods when there is nothing to get, adding a secret to the list,
 * and getting the list and elements from the list.
 */
class SecretEnumeratorTest {

    /**
     * Tests empty getters, adding secrets and retrieving them from the SecretEnumerator.
     * Checks for ArrayIndexOutOfBoundsException for empty enumerator, proper addition of secrets
     * and retrieval of secrets from the enumerator.
     *
     * @throws FolderExistsException if a folder with the same name already exists
     */
    @Test
    void emptyGetters() throws FolderExistsException {
        ArrayList<Secret> list2 = new ArrayList<>();
        Hashtable<String, ArrayList<Secret>> list3 = new Hashtable<String, ArrayList<Secret>>();
        SecretEnumerator enumerator = new SecretEnumerator(list2, list3);
        assertEquals(new ArrayList<>(), enumerator.getList());
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            enumerator.get(1);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            enumerator.get(0);
        });
        Secret secret = new Secret("secret1", "folder1");
        Secret secret2 = new Secret("secret1", "folder1");
        Secret secret3 = new Secret("secret3", "folder3");
        enumerator.add(secret);
        ArrayList<Secret> list = new ArrayList<>();
        list.add(secret);
        assertEquals(secret, enumerator.get(0));
        assertNotEquals(secret2, enumerator.get(0));
        assertEquals(list, enumerator.getList());
        assertEquals(list, enumerator.getList("folder1"));
        enumerator.add(secret3);
        assertEquals(list, enumerator.getList("folder1"));
        list.add(secret3);
        assertEquals(list, enumerator.getList());
    }
}
