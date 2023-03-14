package seedu.duke.secrets;

import seedu.duke.Backend;

public class BasicPassword extends Secret{
    private String username;
    private String password;
    private String url;
    public BasicPassword(String name, String folderName, String username,
                            String password, String url) {
        super(name, folderName);
        this.password = password;
        this.username = username;
        this.url = url;
    }
    @Override
    public String toStringForDatabase() {
        String formattedString =  "Password," + super.toStringForDatabase() +
            "," + this.username + "," + this.password +
               "," + this.url;
        return formattedString;
    }
}
