package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.InvalidURLException;
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
    public AddBasicPasswordCommand(BasicPassword basicPassword) {
        this.name = basicPassword.getName();
        this.folderName = basicPassword.getFolderName();
        this.url = basicPassword.getUrl();
        this.username = basicPassword.getUsername();
        this.password = basicPassword.getPassword();
    }
    @Override
    public void execute(SecretMaster secureNUSData) throws ExceptionMain {
        BasicPassword basicPasswordData;
        try {
            basicPasswordData = new BasicPassword(name,folderName,username,password,url);
        } catch (InvalidURLException e) {
            throw new ExceptionMain("Invalid URL!");
        }

        try {
            secureNUSData.addSecret(basicPasswordData);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException | IllegalSecretNameException | IllegalFolderNameException e) {
            throw new RuntimeException(e);
        }
        String starsPassword = "*".repeat(8);
        System.out.println("I have added a new basic password:\n");
        System.out.println("name     = " + name + "\n" +
                           "folder   = " + folderName + "\n" +
                           "url      = " + url + "\n" +
                           "username = " + username + "\n" +
                           "password = " + starsPassword);
    }

    public String extractName(String input) {
        String extractedName = input.split("new ")[1];
        extractedName = extractedName.split(" /f")[0];
        return extractedName;
    }
    public String extractFolderName(String input) {
        String extractedFolderName = "unnamed";
        if (input.split("/f ").length > 1) {
            extractedFolderName = input.split("/f ")[1];
        }
        return extractedFolderName;
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
