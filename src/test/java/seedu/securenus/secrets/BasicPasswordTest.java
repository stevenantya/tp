package seedu.securenus.secrets;

import org.junit.jupiter.api.Test;
import seedu.securenus.exceptions.secrets.InvalidURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit Test for the constructor of the BasicPassword class.
 */
public class BasicPasswordTest {
    /**
     * Tests creation of basic password without folder.
     * @throws InvalidURLException if URL format is invalid
     */
    @Test
    public void basicPasswordNoFolder() throws InvalidURLException {
        BasicPassword basicPassword =
                new BasicPassword("basicPassword1", "basicUsername", "Lorem Ipsum 112", "google.com");
        assertEquals("basicPassword1", basicPassword.getName());
        assertEquals("unnamed", basicPassword.getFolderName());
        assertEquals("basicUsername", basicPassword.getUsername());
        assertEquals("Lorem Ipsum 112", basicPassword.getPassword());
        assertEquals("google.com", basicPassword.getUrl());
    }

    /**
     * Tests creation of basic password with folder.
     * @throws InvalidURLException if URL format is invalid
     */
    @Test
    public void basicPasswordFolder() throws InvalidURLException {
        BasicPassword basicPassword =
                new BasicPassword("basicPassword1", "FolderName", "basicUsername", "Lorem Ipsum 112", "google.com");
        assertEquals("basicPassword1", basicPassword.getName());
        assertEquals("FolderName", basicPassword.getFolderName());
        assertEquals("basicUsername", basicPassword.getUsername());
        assertEquals("Lorem Ipsum 112", basicPassword.getPassword());
        assertEquals("google.com", basicPassword.getUrl());
    }

}
