package seedu.duke.Command;

import seedu.duke.Ui;
import seedu.duke.secrets.BasicPassword;

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
        this.password = inquirePassword(input);
    }
    @Override
    public void execute(Ui ui) {
//        BasicPassword basicPasswordData = new BasicPassword(name,folderName,username,password,url);
        System.out.println(name + "  " + password);
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
    public String inquirePassword(String input) {
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
