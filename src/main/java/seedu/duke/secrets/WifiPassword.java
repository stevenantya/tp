package seedu.duke.secrets;

/**
 * Main things to export
 * name: Str
 * folderName: Str
 * username: Str (optional)
 * password: Str
 */
public class WifiPassword extends Secret{
    private String username;
    private String password;

    public WifiPassword(String name, String username,
                        String password) {
        super(name);
        // username can be null
        this.password = password;
        this.username = username;
    }

    public WifiPassword(String name, String folderName, String username,
                         String password) {
        super(name, folderName);
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void removeUsername() {
        username = null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRevealStr() {
        return String.format("Password: %s", password);
    }
}
