package seedu.duke.secrets;

import seedu.duke.Backend;

public class NUSNet extends Secret{
    private String nusNetId;
    private String password;
    public NUSNet(String name, String folderName, String nusNetId, String password) {
        super(name, folderName);
        this.nusNetId = nusNetId;
        this.password = password;
    }
    public NUSNet(String name, String nusNetId, String password) {
        super(name, "unnamed");
        this.nusNetId = nusNetId;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String getnusNetId() {
        return nusNetId;
    }

    @Override
    public String toStringForDatabase() {
        String formattedString =  "nusNetId," + super.toStringForDatabase() +
                "," + this.nusNetId + "," + Backend.encode(this.password);
        return formattedString;
    }
}
