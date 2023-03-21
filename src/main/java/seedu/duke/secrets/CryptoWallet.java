package seedu.duke.secrets;

import java.util.ArrayList;

/**
 * Main things to export
 * name: Str
 * folderName: Str
 * username: Str
 * privateKey: Str
 * seedPhrase: Str
 * urls: Str[]
 */
public class CryptoWallet extends Secret {
    private String username;
    private String privateKey;
    private String seedPhrase;
    private ArrayList<String> urls;

    public CryptoWallet(String name, String username,
                        String privateKey, String seedPhrase) {
        super(name);
        urls = new ArrayList<String>();
        this.username = username;
        this.seedPhrase = seedPhrase;
        this.privateKey = privateKey;
    }
    public CryptoWallet(String name, String folderName, String username,
                        String privateKey, String seedPhrase) {
        super(name, folderName);
        urls = new ArrayList<String>();
        this.username = username;
        this.seedPhrase = seedPhrase;
        this.privateKey = privateKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getSeedPhrase() {
        return seedPhrase;
    }

    public void setSeedPhrase(String seedPhrase) {
        this.seedPhrase = seedPhrase;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

    public void addUrl(String url) {
        urls.add(url);
    }
    @Override
    public String getRevealStr() {
        return String.format("Seed Phrase: %s", seedPhrase);
    }
}
