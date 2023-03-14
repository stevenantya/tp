package seedu.duke.Command;

import seedu.duke.Ui;
import seedu.duke.exceptions.FolderExistsException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.secrets.StudentID;
import seedu.duke.storage.SecretMaster;

/**
 * @author : Steven A. O. Waskito
 **/
public class AddStudentIDCommand extends Command {
    private String name;
    private String folderName;
    private String Student_ID;
    public AddStudentIDCommand(String input) {
        this.name = extractName(input);
        this.folderName = extractFolderName(input);
        this.Student_ID = inquireStudentID();
        if (this.name == null) {
            this.name = Student_ID;
        }
    }
    @Override
    public void execute(SecretMaster secureNUSData) {
        StudentID Student_IDData = new StudentID(name,folderName,Student_ID);
        try
        {
            secureNUSData.addSecret(Student_IDData);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException e) {
            throw new RuntimeException(e);
        }
        System.out.println("I have added a new Student_ID:\n");
        System.out.println(
                "name       = " + name + "\n" +
                "Student ID = " + Student_ID);
    }

    public String extractName(String input) {
        String[] extractedNames = input.split("o/StudentID ");
        String extractedName;
        if (extractedNames.length == 2) {
            if (extractedNames[1].split(" /f").length > 1) {
                extractedName = extractedNames[1].split(" /f")[0];
            }
            else {
                extractedName = null;
            }
        }
        else {
            extractedName = null; //Default Name
        }
        return extractedName;
    }
    public String extractFolderName(String input) {
        String extractedFolderName = "unfiled";
        if (input.split("/f ").length > 1) {
            extractedFolderName = input.split("/f ")[1];
        }
        return extractedFolderName;
    }
    public String inquireStudentID() {
        System.out.println("Please enter your Student ID: ");
        String StudentID = Ui.readCommand();
        return StudentID;
    }
    public String extractURL(String input) {
        return "";
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
