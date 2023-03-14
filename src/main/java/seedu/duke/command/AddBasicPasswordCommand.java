package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
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
        this.username = extractUsername(input);
        this.url = extractURL(input);
        this.password = inquirePassword();
    }
    @Override
    public void execute(SecretMaster secureNUSData) throws IllegalFolderNameException, IllegalSecretNameException {
        BasicPassword basicPasswordData = new BasicPassword(name,folderName,username,password,url);
        try {
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
        String password = Ui.readCommand();
        return password;
    }
    public String extractURL(String input) {
        return "";
    }
    @Override
    public boolean isExit() {
        return false;
    }


}
