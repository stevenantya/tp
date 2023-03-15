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
        return input.split("view")[1].trim();
    }



    /*
     * For passwords that are “password-required”, a secret password is required to reveal.
     */
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
        } catch (SecretNotFoundException e) {
            throw new RuntimeException(e);
        }
        // TODO: How to show password?
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
