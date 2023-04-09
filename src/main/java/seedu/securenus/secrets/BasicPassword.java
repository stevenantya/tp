package seedu.securenus.secrets;

import seedu.securenus.Backend;

/**
 * BasicPassword class represents a basic password entry that contains a username,
 * password, and URL.
 */
public class BasicPassword extends Secret{
    public static final String TYPE = "BasicPassword";
    private String username;
    private String password;
    private String url;

    /**
     * Constructs a BasicPassword object with the specified name, username, password, and URL.
     *
     * @param name name of the basic password entry
     * @param username username of the basic password entry
     * @param password password of the basic password entry
     * @param url URL of the basic password entry
     */
    public BasicPassword(String name, String username,
                         String password, String url) {
        super(name);
        this.password = password;
        this.username = username;
        this.url = url;
    }

    /**
     * Constructs a BasicPassword object with the specified name, folder name, username, password, and URL.
     *
     * @param name name of the basic password entry
     * @param folderName name of the folder that the basic password entry is stored in
     * @param username username of the basic password entry
     * @param password password of the basic password entry
     * @param url URL of the basic password entry
     */
    public BasicPassword(String name, String folderName, String username,
                            String password, String url)  {
        super(name, folderName);
        this.password = password;
        this.username = username;
        this.url = url;
    }

    /**
     * Returns the type of the object.
     *
     * @return the type of the object
     */
    public String getType() {
        return TYPE;
    }

    /**
     * Returns the password for basic password entry.
     * @return the password for basic password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the URL for basic password entry.
     *
     * @return URL for the basic password entry
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns a formatted string representation of the BasicPassword object for the database.
     *
     * @return formatted string representation of the BasicPassword object for the database
     */
    @Override
    public String toStringForDatabase() {
        String formattedString =  "Password," + super.toStringForDatabase() +
            "," + Backend.encode(this.username) + "," + Backend.encode(this.password) +
               "," + Backend.encode(this.url);
        return formattedString;
    }

    /**
     * Returns a string representation of the BasicPassword object for revealing the password.
     *
     * @return string representation of the BasicPassword object for revealing the password
     */
    @Override
    public String getRevealStr() {
        return String.format("Name: %s\n" +
                        "Url: %s\n" +
                        "Username: %s\n" +
                        "Password: %s",
                getName(), url, username, password);
    }


    /**
     * Returns the username associated with the password.
     *
     * @return the username of the password
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the object.
     *
     * @param username the username to be set
     * @throws NullPointerException if the username is null
     */
    public void setUsername(String username) {
        assert username != null;
        this.username = username;
    }

    /**
     * Sets the password of the object.
     *
     * @param password the password to be set
     * @throws NullPointerException if the password is null
     */
    public void setPassword(String password) {
        assert password != null;
        this.password = password;
    }

    /**
     * Sets the URL of the object.
     *
     * @param url the URL to be set
     * @throws NullPointerException if the URL is null
     */
    public void setUrl(String url) {
        assert url != null;
        this.url = url;
    }
}
