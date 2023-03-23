package seedu.duke.secrets;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author : Steven A. O. Waskito
 **/
public class StudentIDTest {
    @Test
    public void studentIDTestNoFolder() {
        StudentID studentID = new StudentID("StudentID Name","A021313J");
        assertEquals("StudentID Name", studentID.getName());
        assertEquals("unnamed", studentID.getFolderName());
        assertEquals("A021313J", studentID.getStudentID());
    }

    @Test
    public void studentIDTestFolder() {
        StudentID studentID = new StudentID("StudentID2 Name", "StudentsOfNUS", "A021313G");
        assertEquals("StudentID2 Name", studentID.getName());
        assertEquals("StudentsOfNUS", studentID.getFolderName());
        assertEquals("A021313G", studentID.getStudentID());
    }

}
