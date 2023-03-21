package seedu.duke.secrets;

import seedu.duke.Backend;

public class NUSNet extends Secret{
    private String NUSNet_ID;
    private String password;
    public NUSNet(String name, String folderName, String NUSNet_ID, String password) {
        super(name, folderName);
        this.NUSNet_ID = NUSNet_ID;
        this.password = password;
    }
    public NUSNet(String name, String NUSNet_ID, String password) {
        super(name, "unnamed");
        this.NUSNet_ID = NUSNet_ID;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String getNUSNet_ID() {
        return NUSNet_ID;
    }

    @Override
    public String toStringForDatabase() {
        String formattedString =  "NUSNetID," + super.toStringForDatabase() +
                "," + this.NUSNet_ID + "," + Backend.encode(this.password);
        return formattedString;
    }
}
