package seedu.duke.secrets;

public class NUSNet extends Secret{
    private String NUSNet_ID;
    private String password;
    public NUSNet(String name, String folderName, String NUSNet_ID, String password) {
        super(name, folderName);
        this.NUSNet_ID = NUSNet_ID;
        this.password = password;
    }
}
