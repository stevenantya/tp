package seedu.duke.Command;

import seedu.duke.Ui;
import seedu.duke.exceptions.FolderExistsException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.secrets.NUSNet;
import seedu.duke.storage.SecretMaster;

import java.util.Scanner;

/**
 * @author : Steven A. O. Waskito
 **/
public class AddNUSNetCommand extends Command{
    private String name;
    private String folderName;
    private String NUSNet_ID;
    private String password;
    public AddNUSNetCommand(String input) {
        this.name = extractName(input);
        this.folderName = extractFolderName(input);
        this.NUSNet_ID = inquireNUSNetID();
        if (this.name == null) {
            this.name = NUSNet_ID;
        }
        this.password = inquirePassword();
    }
    @Override
    public void execute(Ui ui, SecretMaster secureNUSData) {
        NUSNet NUSNet_IDData = new NUSNet(name,folderName,NUSNet_ID,password);
        try
        {
            secureNUSData.addSecret(NUSNet_IDData);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException e) {
            throw new RuntimeException(e);
        }
        String starsPassword = "*".repeat(8);
        System.out.println("I have added a new NUS Net ID password:\n");
        System.out.println(
                "name       = " + name + "\n" +
                "NUS Net ID = " + NUSNet_ID + "\n" +
                "password   = " + starsPassword);
    }

    public String extractName(String input) {
        String[] extractedNames = input.split("o/NUSNet ");
        String extractedName;
        if (extractedNames.length == 2) {
            extractedName = extractedNames[1];
        }
        else {
            extractedName = null; //Default Name
        }
        return extractedName;
    }
    public String extractFolderName(String input) {
        return "";
    }
    public String inquireNUSNetID() {
        System.out.println("Please enter your NUS Net ID: ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public String inquirePassword() {
        System.out.println("Please enter your NUS Net password: ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public String extractURL(String input) {
        return "";
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
