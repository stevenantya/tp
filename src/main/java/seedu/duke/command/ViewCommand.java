package seedu.duke.command;

import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

import java.util.Scanner;

public class ViewCommand extends Command {
    private final String passwordName;
    public ViewCommand(String input) {
        this.passwordName = extractName(input);
    }
    public String extractName(String input) {
        return input.substring(input.indexOf("p/") + 2).trim();
    }

    public String inquirePassword() {
        System.out.println("Enter secret password to reveal \"" + this.passwordName + "\":");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    @Override
    public void execute(SecretMaster secureNUSData) {
        Secret passwordSecret;
        try {
            passwordSecret = secureNUSData.getByName(this.passwordName);
            System.out.println(passwordSecret.getRevealStr());
        } catch (SecretNotFoundException e) {
            System.out.println("There are no passwords that matches that name!\n" +
                    "Make sure you follow this format: \"view p/PASSWORD_NAME\"");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
