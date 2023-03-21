package seedu.duke.secrets;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CryptoWalletCredentialsTest {

    @Test
    void noFolderCryptoWalletCreationTest() {
        CryptoWalletCredentials cryptoWalletCredentials =
                new CryptoWalletCredentials("cryptoWallet1", "hoseang",
                        "234567uygfde45tghjioiuytre3", "hbhh jh jhy tgjm ktr de sed fe sf");
        assertEquals("unnamed", cryptoWalletCredentials.getFolderName());
        assertEquals("234567uygfde45tghjioiuytre3", cryptoWalletCredentials.getPrivateKey());
        assertEquals("hoseang", cryptoWalletCredentials.getUsername());
        assertEquals("hbhh jh jhy tgjm ktr de sed fe sf", cryptoWalletCredentials.getSeedPhrase());
        assertEquals("cryptoWallet1", cryptoWalletCredentials.getName());
        assertEquals("cryptoWallet1", cryptoWalletCredentials.getUid());
    }

    @Test
    void withFolderCryptoWalletCreationTest() {
        CryptoWalletCredentials cryptoWalletCredentials =
                new CryptoWalletCredentials("cryptoWallet1", "folder1", "hoseang",
                        "234567uygfde45tghjioiuytre3", "hbhh jh jhy tgjm ktr de sed fe sf");
        assertEquals("folder1", cryptoWalletCredentials.getFolderName());
        assertEquals("234567uygfde45tghjioiuytre3", cryptoWalletCredentials.getPrivateKey());
        assertEquals("hoseang", cryptoWalletCredentials.getUsername());
        assertEquals("hbhh jh jhy tgjm ktr de sed fe sf", cryptoWalletCredentials.getSeedPhrase());
        assertEquals("cryptoWallet1", cryptoWalletCredentials.getName());
        assertEquals("cryptoWallet1", cryptoWalletCredentials.getUid());
    }

    void editingDetails() {
        CryptoWalletCredentials cryptoWalletCredentials =
                new CryptoWalletCredentials("cryptoWallet1", "folder1", "hoseang",
                        "234567uygfde45tghjioiuytre3", "hbhh jh jhy tgjm ktr de sed fe sf");
        cryptoWalletCredentials.setSeedPhrase("fdfghj rewfs weqf weqf weqr fwer weqrgfdvsg thr fdc sda");
        cryptoWalletCredentials.setFolderName("folder2");
        cryptoWalletCredentials.setName("cryptoWallet2");
        ArrayList<String> urls = new ArrayList<>();
        assertEquals(new ArrayList<String>(), cryptoWalletCredentials.getUrls());
        urls.add("http.guefhwbqhyhwuijrf.com");
        urls.add("http.guefhwbqhydbdfdf.com");
        cryptoWalletCredentials.setUrls(urls);
        assertEquals(urls, cryptoWalletCredentials.getUrls());
        // adding urls
        cryptoWalletCredentials.addUrl("http://newsite.com");
        urls.add("http://newsite.com");
        assertEquals(urls, cryptoWalletCredentials.getUrls());
        assertEquals(3, cryptoWalletCredentials.getUrls().size());
        assertEquals("folder2", cryptoWalletCredentials.getFolderName());
        assertEquals("234567uygfde45tghjioiuytre3", cryptoWalletCredentials.getPrivateKey());
        assertEquals("hoseang", cryptoWalletCredentials.getUsername());
        assertEquals("fdfghj rewfs weqf weqf weqr fwer weqrgfdvsg thr fdc sda",
                cryptoWalletCredentials.getSeedPhrase());
        assertEquals("cryptoWallet2", cryptoWalletCredentials.getName());
        assertEquals("cryptoWallet2", cryptoWalletCredentials.getUid());
    }
}
