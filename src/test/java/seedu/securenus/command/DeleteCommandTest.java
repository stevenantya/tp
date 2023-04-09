package seedu.securenus.command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.securenus.exceptions.ExceptionMain;

import seedu.securenus.exceptions.InsufficientParamsException;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.InvalidURLException;
import seedu.securenus.exceptions.secrets.NonExistentFolderException;
import seedu.securenus.exceptions.secrets.SecretNotFoundException;
import seedu.securenus.secrets.BasicPassword;

import seedu.securenus.secrets.NUSNet;
import seedu.securenus.secrets.StudentID;
import seedu.securenus.storage.SecretMaster;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JUnit test class for DeleteCommand.
 */
public class DeleteCommandTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Sets up the output stream before each test.
     */
    @BeforeEach
    public void setStream() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    /**
     * Tests deleting Student ID from the folder.
     *
     * @throws SecretNotFoundException   If the secret is not found.
     * @throws ExceptionMain             If an error occurs while executing the command.
     * @throws InvalidURLException       If the URL of NUSNet is invalid.
     * @throws InsufficientParamsException If there are insufficient parameters for the command.
     * @throws OperationCancelException  If the operation is cancelled.
     * @throws FolderExistsException     If the folder already exists.
     * @throws NonExistentFolderException If the folder does not exist.
     */
    @Test
    public void studentIDTestFolder() throws SecretNotFoundException, ExceptionMain, InvalidURLException,
            InsufficientParamsException, OperationCancelException, FolderExistsException, NonExistentFolderException {
        SecretMaster sm = new SecretMaster();

        StudentID studentID = new StudentID("StudentID2Name", "StudentsOfNUS", "A021313G");
        Command addStudentId = new AddStudentIDCommand(studentID);
        addStudentId.execute(sm);
        assertEquals("StudentID2Name",sm.getByName("StudentID2Name").getName());

        NUSNet nusNet = new NUSNet("NUSNetName2", "FolderName", "e081888@u.nus.edu", "Lorem Ipsum 12");
        Command addNusNet = new AddNUSNetCommand(nusNet);
        addNusNet.execute(sm);
        assertEquals("NUSNetName2", sm.getByName("NUSNetName2").getName());

        BasicPassword basicPassword =
                new BasicPassword("basicPassword1", "FolderName", "basicUsername", "Lorem Ipsum 112", "google.com");
        Command addBasicPassword = new AddBasicPasswordCommand(basicPassword);
        addBasicPassword.execute(sm);
        assertEquals("basicPassword1", sm.getByName("basicPassword1").getName());


        Command deleteStudentID = new DeleteCommand("delete StudentID2Name NUSNetName2 basicPassword1");
        deleteStudentID.execute(sm);
        assertThrows(SecretNotFoundException.class, () -> {
                sm.getByName("StudentID2Name");
            }
        );
        assertThrows(SecretNotFoundException.class, () -> {
                sm.getByName("NUSNetName2");
            }
        );
        assertThrows(SecretNotFoundException.class, () -> {
                sm.getByName("basicPassword1");
            }
        );
    }
}
