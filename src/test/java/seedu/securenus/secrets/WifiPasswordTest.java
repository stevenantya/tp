package seedu.securenus.secrets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test class for the WifiPassword class.
 */
class WifiPasswordTest {

    /**
     * Tests the creation of a WifiPassword object without a folder.
     */
    @Test
    void noFolderWifiPasswordCreationTest() {
        WifiPassword wifiPassword = new WifiPassword("wifi1", "admin", "Password");
        assertEquals("wifi1", wifiPassword.getName());
        assertEquals("admin", wifiPassword.getUsername());
        assertEquals("Password", wifiPassword.getPassword());
        assertEquals("wifi1", wifiPassword.getUid());
        assertEquals("unnamed", wifiPassword.getFolderName());
        wifiPassword.removeUsername();
        assertEquals(null, wifiPassword.getUsername());

        // for no username
        WifiPassword wifiPassword2 = new WifiPassword("wifi1", null, "Password");
        assertEquals("wifi1", wifiPassword2.getName());
        assertEquals(null, wifiPassword2.getUsername());
        assertEquals("Password", wifiPassword2.getPassword());
        assertEquals("wifi1", wifiPassword2.getUid());
        assertEquals("unnamed", wifiPassword2.getFolderName());
        wifiPassword2.setPassword("P@ssw0rd2");
        assertEquals("P@ssw0rd2", wifiPassword2.getPassword());
    }

    /**
     * Tests the creation of a WifiPassword object with a folder.
     */
    @Test
    void withFolderWifiPasswordCreationTest() {
        WifiPassword wifiPassword = new WifiPassword("wifi1", "folder1", "admin",
                "Password");
        assertEquals("wifi1", wifiPassword.getName());
        assertEquals("admin", wifiPassword.getUsername());
        assertEquals("Password", wifiPassword.getPassword());
        assertEquals("wifi1", wifiPassword.getUid());
        assertEquals("folder1", wifiPassword.getFolderName().toString());
        wifiPassword.removeUsername();
        assertEquals(null, wifiPassword.getUsername());

        // for no username
        WifiPassword wifiPassword2 = new WifiPassword("wifi1", "folder1", null,
                "Password");
        assertEquals("wifi1", wifiPassword2.getName());
        assertEquals(null, wifiPassword2.getUsername());
        assertEquals("Password", wifiPassword2.getPassword());
        assertEquals("wifi1", wifiPassword2.getUid());
        assertEquals("folder1", wifiPassword2.getFolderName().toString());
        wifiPassword2.setPassword("P@ssw0rd2");
        assertEquals("P@ssw0rd2", wifiPassword2.getPassword());
    }

    /**
     * Tests the getRevealStr() method of the WifiPassword class.
     */
    @Test
    void testRevealOutput() {
        WifiPassword wifiPassword = new WifiPassword("wifi1", "folder1", "admin",
                "P@ssword1");
        assertEquals("Name: wifi1\n" +
                "Username: admin\n" +
                "Password: P@ssword1", wifiPassword.getRevealStr());
        WifiPassword wifiPassword2 = new WifiPassword("wifi1", "admin",
                "P@ssword1");
        assertEquals("Name: wifi1\n" +
                "Username: admin\n" +
                "Password: P@ssword1", wifiPassword2.getRevealStr());
    }
}
