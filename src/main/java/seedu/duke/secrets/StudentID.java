package seedu.duke.secrets;

public class StudentID extends Secret {
    private String studentID;
    private String password;

    public StudentID(String name, String folderName, String studentID, String password) {
        super(name, folderName);
        this.studentID = studentID;
        this.password = password;
    }
}
