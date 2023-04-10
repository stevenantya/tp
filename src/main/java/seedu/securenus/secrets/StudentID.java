package seedu.securenus.secrets;

import seedu.securenus.SecureNUSLogger;

import java.util.logging.Level;

/**
 * This class represents a student ID secret.
 * It inherits from the Secret class.
 */
public class StudentID extends Secret {
    public static final String TYPE = "StudentID";
    private static final String ACCEPTED_ID_REGEX = "A[0-9]{7}[A-Z]";
    private String studentID;

    /**
     * Constructs a new StudentID object with the specified name and student ID.
     *
     * @param name the name of the student ID secret
     * @param studentID the student ID number
     */
    public StudentID(String name, String studentID) {
        super(name);
        this.studentID = studentID;
    }

    /**
     * Constructs a new StudentID object with the specified name, folder name, and student ID.
     *
     * @param name the name of the student ID secret
     * @param folderName the folder name of the student ID secret
     * @param studentID the student ID number
     */
    public StudentID(String name, String folderName, String studentID) {
        super(name, folderName);
        this.studentID = studentID;
    }

    /**
     * Returns the type of the secret.
     *
     * @return the type of the secret
     */
    public String getType() {
        return TYPE;
    }

    /**
     * Checks if the given student ID is legal
     *
     * @param studentId the student ID to be checked
     * @return true if the student ID is legal, false otherwise
     */
    public static boolean isLegalId(String studentId) {
        if (!studentId.matches(ACCEPTED_ID_REGEX)) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, Student ID is illegal, " + studentId);
        }
        return studentId.matches(ACCEPTED_ID_REGEX);
    }

    /**
     * Returns the student ID number.
     *
     * @return the student ID number
     */
    public String getStudentID() {
        return studentID;
    }
    /**
     * Returns a string representation of the StudentID object for storing in the database.
     *
     * @return a string representation of the StudentID object
     */
    @Override
    public String toStringForDatabase() {
        String formattedString =  "studentID," + super.toStringForDatabase() +
            "," + this.studentID;
        return formattedString;
    }

    /**
     * Returns a string representation of the StudentID object.
     *
     * @return a string representation of the StudentID object
     */
    @Override
    public String getRevealStr() {
        return String.format("Name: %s\n" +
                        "StudentID: %s",
                getName(), studentID);
    }

    /**
     * Sets the student ID of the StudentID object.
     *
     * @param studentID the student ID to be set
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
