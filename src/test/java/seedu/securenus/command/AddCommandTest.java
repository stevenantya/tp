package seedu.securenus.command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.securenus.exceptions.ExceptionMain;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.NonExistentFolderException;
import seedu.securenus.exceptions.secrets.SecretNotFoundException;
import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.secrets.NUSNet;
import seedu.securenus.secrets.StudentID;
import seedu.securenus.storage.SecretMaster;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test class for AddCommand.
 */
public class AddCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Redirects System.out to outputStreamCaptor.
     */
    @BeforeEach
    public void setStream() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Tests adding a student ID to a folder.
     * @throws SecretNotFoundException if the secret is not found in the secret list.
     * @throws ExceptionMain if a general exception is caught.
     * @throws OperationCancelException if the user decides to cancel the operation.
     * @throws FolderExistsException if a folder with the same name already exists.
     * @throws NonExistentFolderException if the folder does not exist in the folder list.
     */
    @Test
    public void studentIDTestFolder() throws SecretNotFoundException, ExceptionMain,
            OperationCancelException, FolderExistsException, NonExistentFolderException {
        SecretMaster sm = new SecretMaster();
        StudentID studentID = new StudentID("StudentID2Name", "StudentsOfNUS", "A021313G");
        Command addStudentId = new AddStudentIDCommand(studentID);
        addStudentId.execute(sm);
        assertEquals("StudentID2Name",sm.getByName("StudentID2Name").getName());
    }

    /**
     * Tests adding a NUSNet account to a folder.
     * @throws SecretNotFoundException if the secret is not found in the secret list.
     * @throws ExceptionMain if a general exception is caught.
     * @throws OperationCancelException if the user decides to cancel the operation.
     * @throws FolderExistsException if a folder with the same name already exists.
     * @throws NonExistentFolderException if the folder does not exist in the folder list.
     */
    @Test
    public void nusNetFolder() throws SecretNotFoundException, ExceptionMain, OperationCancelException,
            FolderExistsException, NonExistentFolderException {
        SecretMaster sm = new SecretMaster();
        NUSNet nusNet = new NUSNet("NUSNetName2", "FolderName", "e081888@u.nus.edu", "Lorem Ipsum 12");
        Command addNusNet = new AddNUSNetCommand(nusNet);
        addNusNet.execute(sm);
        assertEquals("NUSNetName2", sm.getByName("NUSNetName2").getName());
    }


    /**
     * Tests adding a basic password to a folder.
     * @throws SecretNotFoundException if the secret is not found in the secret list.
     * @throws ExceptionMain if a general exception is caught.
     * @throws OperationCancelException if the user decides to cancel the operation.
     * @throws FolderExistsException if a folder with the same name already exists.
     * @throws NonExistentFolderException if the folder does not exist in the folder list.
     */
    @Test
    void basicPasswordFolder() throws
            SecretNotFoundException, ExceptionMain, OperationCancelException, FolderExistsException,
            NonExistentFolderException {
        SecretMaster sm = new SecretMaster();
        BasicPassword basicPassword =
                new BasicPassword("basicPassword1", "FolderName", "basicUsername", "Lorem Ipsum 112", "google.com");
        Command addBasicPassword = new AddBasicPasswordCommand(basicPassword);
        addBasicPassword.execute(sm);
        assertEquals("basicPassword1", sm.getByName("basicPassword1").getName());
    }
}
