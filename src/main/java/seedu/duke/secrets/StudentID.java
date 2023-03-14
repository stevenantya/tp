package seedu.duke.secrets;

public class StudentID extends Secret {
    private String studentID;

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
}
