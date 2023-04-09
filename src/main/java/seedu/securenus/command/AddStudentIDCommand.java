package seedu.securenus.command;

import seedu.securenus.exceptions.ExceptionMain;
import seedu.securenus.exceptions.OperationCancelException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.messages.InquiryMessages;
import seedu.securenus.secrets.StudentID;
import seedu.securenus.storage.SecretMaster;
import seedu.securenus.ui.Ui;

import java.util.HashSet;

/**
 * Represents the class to give a command to add a new Student ID to the SecureNUS system.
 */
public class AddStudentIDCommand extends AddSecretCommand {
    public static final String KEYWORD = "o/StudentID";
    private String studentId;

    /**
     * Constructs an AddStudentIDCommand object with the user input as the command parameter.
     *
     * @param input The command input entered by the user.
     */
    public AddStudentIDCommand(String input, HashSet<String> usedNames) throws IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException, OperationCancelException {
        super(input, usedNames, KEYWORD);
        this.studentId = inquireStudentID();
    }
    public AddStudentIDCommand(StudentID studentID) {
        this.name = studentID.getName();
        this.folderName = studentID.getFolderName();
        this.studentId = studentID.getStudentID();
    }

    /**
     * Executes the AddStudentIDCommand to add a new Student ID to the SecureNUS system.
     *
     * @param secureNUSData
     */

    @Override
    public void execute(SecretMaster secureNUSData) throws ExceptionMain {
        assert secureNUSData != null;
        StudentID studentIdData = new StudentID(name,folderName,studentId);
        try {
            secureNUSData.addSecret(studentIdData);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (IllegalSecretNameException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (IllegalFolderNameException e) {
            throw new ExceptionMain(e.getMessage());
        }
        Ui.inform("I have added a new Student ID:\n" +
                "name       = " + name + "\n" +
                "Folder     = " + folderName + "\n" +
                "Student ID = " + HIDDEN_FIELD);
    }


    /**
     * Prompts the user to enter the Student ID.
     *
     * @return The Student ID entered by the user.
     */
    public String inquireStudentID() throws OperationCancelException {
        String studentID = inquire(InquiryMessages.STUDENT_ID, "Student ID");
        while (!StudentID.isLegalId(studentID)) {
            studentID = inquire(InquiryMessages.STUDENT_ID_RETRY, "Student ID");
        }
        return studentID;
    }
}
