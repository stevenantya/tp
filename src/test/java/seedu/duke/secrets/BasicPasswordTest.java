package seedu.duke.secrets;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.secrets.InvalidExpiryDateException;
import seedu.duke.exceptions.secrets.InvalidURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 **/
public class BasicPasswordTest {
    //Basic Password
    @Test
    public void BasicPasswordNoFolder() throws InvalidURLException {
        BasicPassword basicPassword = new BasicPassword("basicPassword1", "basicUsername", "Lorem Ipsum 112", "google.com");
        assertEquals("basicPassword1", basicPassword.getName());
        assertEquals("unnamed", basicPassword.getFolderName());
        assertEquals("basicUsername", basicPassword.getUsername());
        assertEquals("Lorem Ipsum 112", basicPassword.getPassword());
        assertEquals("google.com", basicPassword.getUrl());
    }

    @Test
    public void BasicPasswordFolder() throws InvalidURLException {
        BasicPassword basicPassword = new BasicPassword("basicPassword1", "FolderName", "basicUsername", "Lorem Ipsum 112", "google.com");
        assertEquals("basicPassword1", basicPassword.getName());
        assertEquals("FolderName", basicPassword.getFolderName());
        assertEquals("basicUsername", basicPassword.getUsername());
        assertEquals("Lorem Ipsum 112", basicPassword.getPassword());
        assertEquals("google.com", basicPassword.getUrl());
    }

}
