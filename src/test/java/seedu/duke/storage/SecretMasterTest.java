package seedu.duke.storage;

//import org.junit.jupiter.api.Test;
//import seedu.duke.exceptions.RepeatedIdException;
//import seedu.duke.exceptions.secrets.FolderExistsException;
//import seedu.duke.exceptions.secrets.IllegalFolderNameException;
//import seedu.duke.exceptions.secrets.IllegalSecretNameException;
//import seedu.duke.secrets.Secret;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Hashtable;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;

//class SecretMasterTest {
//
//    @Test
//    void isLegalName() throws IllegalFolderNameException, FolderExistsException {
//        ArrayList<Secret> list2 = new ArrayList<>();
//        Hashtable<String, ArrayList<Secret>> list3 = new Hashtable<String, ArrayList<Secret>>();
//        SecretEnumerator enumerator = new SecretEnumerator(list2, list3);
//        SecretSearcher
//        SecretMaster secretMaster = new SecretMaster(list2, list3);
//        assertEquals(true, secretMaster.isLegalName("gyujnuygvjkm"));
//        assertEquals(true, secretMaster.isLegalName("hfjewqsdierjdfhnreqwewqfvsvd"));
//        assertEquals(true, secretMaster.isLegalName("fvdwhjejsdkjfk879809"));
//        assertEquals(false, secretMaster.isLegalName("jhgfhdwv "));
//        assertEquals(false, secretMaster.isLegalName("jkfewrjfv90r93f47   "));
//        assertEquals(false, secretMaster.isLegalName("jkfewrjfv90r93f47^&IO(*&^"));
//    }
//
//    @Test
//    void createFolder() throws IllegalFolderNameException, FolderExistsException, RepeatedIdException,
//            IllegalSecretNameException {
//        SecretMaster secretMaster = new SecretMaster();
//        HashSet<String> set = new HashSet<String>();
//        set.add("unnamed");
//        assertEquals(set, secretMaster.getFolders());
//        assertThrows(IllegalFolderNameException.class, () -> {
//            secretMaster.createFolder("\"jkfewrjfv90r93f47^&IO(*&^");
//        });
//        assertThrows(IllegalFolderNameException.class, () -> {
//            secretMaster.createFolder("jhfe ");
//        });
//        assertEquals(set, secretMaster.getFolders());
//        Secret secret1 = new Secret("blimp");
//        secretMaster.addSecret(secret1);
//        assertEquals(set, secretMaster.getFolders());
//        Secret secret2 = new Secret("blimp", "folder1");
//        Secret secret3 = new Secret("blimp2", "folder1");
//        set.add(secret2.getFolderName());
//        System.out.println(set);
//        assertNotEquals(set, secretMaster.getFolders());
//        assertThrows(RepeatedIdException.class, () -> {
//            secretMaster.addSecret(secret2);
//        });
//        secretMaster.addSecret(secret3);
//        assertEquals(set, secretMaster.getFolders());
//    }
//
//    @Test
//    void getByIndex() {
//    }
//
//    @Test
//    void testGetByIndex() {
//    }
//
//    @Test
//    void listSecrets() {
//    }
//
//    @Test
//    void testListSecrets() {
//    }
//
//    @Test
//    void getByName() {
//    }
//
//    @Test
//    void addSecret() {
//    }
//
//    @Test
//    void removeSecret() {
//    }
//}
