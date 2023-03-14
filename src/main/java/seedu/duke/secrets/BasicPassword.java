package seedu.duke.secrets;

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
}
