package seedu.duke.Command;

import seedu.duke.Ui;
import seedu.duke.exceptions.FolderExistsException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.secrets.BasicPassword;
import seedu.duke.storage.SecretMaster;

import java.util.Scanner;

/**
 * @author : Steven A. O. Waskito
 **/
public class AddBasicPasswordCommand extends Command{

    private String name;
    private String folderName;
    private String username;
    private String password;
    private String url;
    public AddBasicPasswordCommand(String input) {
        this.name = extractName(input);
        this.folderName = extractFolderName(input);
        this.username = extractUsername(input);
        this.url = extractURL(input);
        this.password = inquirePassword();
    }
    @Override
    public void execute(Ui ui, SecretMaster secureNUSData) {
        BasicPassword basicPasswordData = new BasicPassword(name,folderName,username,password,url);
        try
        {
            secureNUSData.addSecret(basicPasswordData);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException e) {
            throw new RuntimeException(e);
        }
        String starsPassword = "*".repeat(8);
        System.out.println("I have added a new basic password:\n");
        System.out.println("name     =" + name + "\n" +
                           "password = " + starsPassword);
    }

    public String extractName(String input) {
        String extractedName = input.split("new")[1];
        return extractedName;
    }
    public String extractFolderName(String input) {
        return "";
    }
    public String extractUsername(String input) {
        return "";
    }
    public String inquirePassword() {
        System.out.println("Please enter your password: ");
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
