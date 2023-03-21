package seedu.duke.secrets;

import seedu.duke.Backend;
import seedu.duke.exceptions.secrets.InvalidURLException;

public class BasicPassword extends Secret{
    private String username;
    private String password;
    private String url;
    public BasicPassword(String name, String username,
                         String password, String url) throws InvalidURLException {
        super(name);
        this.password = password;
        this.username = username;
        if (url.contains(".") && (url.indexOf(".") == url.lastIndexOf("."))) {
            this.url = url;
        } else {
            throw new InvalidURLException();
        }
    }
    public BasicPassword(String name, String folderName, String username,
                            String password, String url) throws InvalidURLException {
        super(name, folderName);
        this.password = password;
        this.username = username;
        if (url.contains(".") && (url.indexOf(".") == url.lastIndexOf("."))) {
            this.url = url;
        } else {
            throw new InvalidURLException();
        }
    }
    public String getPassword() {
        return password;
    }
    public String getUrl() {
        return url;
    }
    @Override
    public String toStringForDatabase() {
        String formattedString =  "Password," + super.toStringForDatabase() +
            "," + Backend.encode(this.username) + "," + Backend.encode(this.password) +
               "," + this.url;
        if (this.url.length() == 0) {
            formattedString += "empty";
        }
        return formattedString;
    }

    public String getUsername() {
        return username;
    }
}
