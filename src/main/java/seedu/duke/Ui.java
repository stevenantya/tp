package seedu.duke;

import java.util.Scanner;
public class Ui {
    Scanner in = new Scanner(System.in);

    public void greetUser() {
        System.out.println("Greets User");
    }

    public void printLine() {
        System.out.print("_____________________________________________________");
    }

    public String readCommand() {
        return in.nextLine();
    }

}
