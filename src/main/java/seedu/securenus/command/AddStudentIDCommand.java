package seedu.securenus.command;

import seedu.securenus.Backend;
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
     * Constructs a command for adding a new student ID entry to a password manager.
     *
     * @param input the user input string that triggered the command
     * @param usedNames a set of names that have already been used in the password manager
     * @throws IllegalFolderNameException if the folder name is illegal
     * @throws IllegalSecretNameException if the name of the secret entry is illegal
     * @throws RepeatedIdException if the password manager already contains an entry with the same ID
     * @throws OperationCancelException if the operation is cancelled by the user
     */
    public AddStudentIDCommand(String input, HashSet<String> usedNames) throws IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException, OperationCancelException {
        super(input, usedNames, KEYWORD);
        this.studentId = inquireStudentID();
    }

    /**
     * Constructs a command for adding a new student ID entry to a password manager, using an existing student ID
     * object.
     *
     * @param studentID the existing student ID object to use for constructing the command
     */
    public AddStudentIDCommand(StudentID studentID) {
        this.name = studentID.getName();
        this.folderName = studentID.getFolderName();
        this.studentId = studentID.getStudentID();
    }

    /**
     * Executes the command to add a new student ID entry to a password manager.
     *
     * @param secureNUSData the password manager to add the new student ID entry to
     * @throws ExceptionMain if an error occurs during the execution of the command
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

        Backend.updateStorage(secureNUSData.listSecrets());
    }

    /**
     * Prompts the user to enter a valid student ID.
     *
     * @return the student ID entered by the user
     * @throws OperationCancelException if the user cancels the operation
     */
    public String inquireStudentID() throws OperationCancelException {
        String studentID = inquire(InquiryMessages.STUDENT_ID, "Student ID");
        while (!StudentID.isLegalId(studentID)) {
            studentID = inquire(InquiryMessages.STUDENT_ID_RETRY, "Student ID");
        }
        return studentID;
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false, as it is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
