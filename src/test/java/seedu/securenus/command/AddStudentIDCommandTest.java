package seedu.securenus.command;

import org.junit.jupiter.api.Test;
import seedu.securenus.secrets.StudentID;

import static org.junit.jupiter.api.Assertions.assertFalse;

class AddStudentIDCommandTest {

    @Test
    void isExit() {
        AddStudentIDCommand addStudentID = new AddStudentIDCommand(new StudentID("Name","A098765X"));
        assertFalse(addStudentID.isExit());
    }
}
