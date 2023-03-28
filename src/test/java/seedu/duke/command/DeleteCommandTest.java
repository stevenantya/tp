package seedu.duke.command;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ExceptionMain;

import seedu.duke.exceptions.secrets.InvalidExpiryDateException;
import seedu.duke.exceptions.secrets.InvalidURLException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.CreditCard;
import seedu.duke.secrets.CryptoWallet;
import seedu.duke.secrets.WifiPassword;

import seedu.duke.secrets.NUSNet;
import seedu.duke.secrets.StudentID;
import seedu.duke.storage.SecretMaster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author : Steven A. O. Waskito
 **/
public class DeleteCommandTest {
    //Missing Wi-Fi password
    @Test
    public void studentIDTestFolder() throws SecretNotFoundException, ExceptionMain {
        SecretMaster SM = new SecretMaster();
        StudentID studentID = new StudentID("StudentID2Name", "StudentsOfNUS", "A021313G");
        Command addStudentId = new AddStudentIDCommand(studentID);
        addStudentId.execute(SM);
        assertEquals("StudentID2Name",SM.getByName("StudentID2Name").getName());
        Command deleteStudentID = new DeleteCommand("delete StudentID2Name /f StudentsOfNUS");
        deleteStudentID.execute(SM);
        assertThrows(SecretNotFoundException.class, () -> {
                SM.getByName("StudentID2Name");
            }
        );
    }
    @Test
    public void nusNetFolder() throws SecretNotFoundException, ExceptionMain {
        SecretMaster SM = new SecretMaster();
        NUSNet nusNet = new NUSNet("NUSNetName2", "FolderName", "e081888@u.nus.edu", "Lorem Ipsum 12");
        Command addNusNet = new AddNUSNetCommand(nusNet);
        addNusNet.execute(SM);
        assertEquals("NUSNetName2", SM.getByName("NUSNetName2").getName());
        Command deleteNusNet = new DeleteCommand("delete NUSNetName2 /f FolderName");
        deleteNusNet.execute(SM);
        assertThrows(SecretNotFoundException.class, () -> {
                SM.getByName("NUSNetName2");
            }
        );
    }
    @Test
    void basicPasswordFolder() throws InvalidURLException, SecretNotFoundException, ExceptionMain {
        SecretMaster SM = new SecretMaster();
        BasicPassword basicPassword =
                new BasicPassword("basicPassword1", "FolderName", "basicUsername", "Lorem Ipsum 112", "google.com");
        Command addBasicPassword = new AddBasicPasswordCommand(basicPassword);
        addBasicPassword.execute(SM);
        assertEquals("basicPassword1", SM.getByName("basicPassword1").getName());
        Command deleteBasicPassword = new DeleteCommand("delete basicPassword1 /f FolderName");
        deleteBasicPassword.execute(SM);
        assertThrows(SecretNotFoundException.class, () -> {
            SM.getByName("basicPassword1");
            }
        );
    }
}
