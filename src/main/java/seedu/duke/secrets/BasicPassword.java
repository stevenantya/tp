package seedu.duke.secrets;

public class BasicPassword extends Secret{
    private static String username;
    private static String password;
    private static String url;
    public BasicPassword(String name, String folderName, String username,
                            String password, String URL) {
        super(name, folderName);
        this.password = password;
        this.username = username;
        this.url = url;
    }
}
