package seedu.duke.secrets;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 **/
public class StudentIDTest {
    @Test
    public void StudentIDTestNoFolder() {
        StudentID studentID = new StudentID("StudentID Name","A021313J");
        assertEquals("StudentID Name", studentID.getName());
        assertEquals("unnamed", studentID.getFolderName());
        assertEquals("A021313J", studentID.getStudentID());
    }

    @Test
    public void StudentIDTestFolder() {
        StudentID studentID = new StudentID("StudentID2 Name", "StudentsOfNUS", "A021313G");
        assertEquals("StudentID2 Name", studentID.getName());
        assertEquals("StudentsOfNUS", studentID.getFolderName());
        assertEquals("A021313G", studentID.getStudentID());
    }

}
