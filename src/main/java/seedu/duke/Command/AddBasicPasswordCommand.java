package seedu.duke.Command;

import seedu.duke.Ui;
import seedu.duke.exceptions.FolderExistsException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.secrets.BasicPassword;
import seedu.duke.storage.SecretMaster;


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
        this.url = inquireURL(input);
        this.username = inquireUsername(input);
        this.password = inquirePassword();
    }
    @Override
    public void execute(SecretMaster secureNUSData) {
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
        System.out.println("name     = " + name + "\n" +
                           "url      = " + url + "\n" +
                           "username = " + username + "\n" +
                           "password = " + starsPassword);
    }

    public String extractName(String input) {
        String extractedName = input.split("new ")[1];
        return extractedName;
    }
    public String extractFolderName(String input) {
        return "";
    }
    // Currently prompts the user to input the url separately
    public String inquireUsername(String input) {
        System.out.println("Please enter your username: ");
        String username = Ui.readCommand();
        return username;
    }
    public String inquirePassword() {
        System.out.println("Please enter your password: ");
        String password = Ui.readCommand();
        return password;
    }
    // Currently prompts the user to input the url separately
    public String inquireURL(String input) {
        System.out.println("Please enter the url: ");
        String url = Ui.readCommand();
        return url;
    }
    @Override
    public boolean isExit() {
        return false;
    }


}
