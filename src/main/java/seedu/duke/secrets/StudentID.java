package seedu.duke.secrets;

public class StudentID extends Secret {
    private String studentID;

    public StudentID(String name, String studentID) {
        super(name);
        this.studentID = studentID;
    }
    public StudentID(String name, String folderName, String studentID) {
        super(name, folderName);
        this.studentID = studentID;
    }

    @Override
    public String toStringForDatabase() {
        String formattedString =  "studentID," + super.toStringForDatabase() +
            "," + this.studentID;
        return formattedString;
    }

    public String getStudentID() {
        return studentID;
    }
    @Override
    public String getRevealStr() {
        return String.format("Name: %s\n" +
                        "StudentID: %s",
                getName(), studentID);
    }
}
