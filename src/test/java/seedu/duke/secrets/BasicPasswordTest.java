package seedu.duke.secrets;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.secrets.InvalidURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Steven A. O. Waskito
 **/
public class BasicPasswordTest {
    //Basic Password
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
