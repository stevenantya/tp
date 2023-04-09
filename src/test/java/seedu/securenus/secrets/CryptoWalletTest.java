package seedu.securenus.secrets;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for the CryptoWallet class.
 */
class CryptoWalletTest {

    /**
     * Tests creation of a CryptoWallet with no folder specified.
     */
    @Test
    void noFolderCryptoWalletCreationTest() {
        CryptoWallet cryptoWallet =
                new CryptoWallet("cryptoWallet1", "hoseang",
                        "234567uygfde45tghjioiuytre3", "hbhh jh jhy tgjm ktr de sed fe sf");
        assertEquals("unnamed", cryptoWallet.getFolderName());
        assertEquals("234567uygfde45tghjioiuytre3", cryptoWallet.getPrivateKey());
        assertEquals("hoseang", cryptoWallet.getUsername());
        assertEquals("hbhh jh jhy tgjm ktr de sed fe sf", cryptoWallet.getSeedPhrase());
        assertEquals("cryptoWallet1", cryptoWallet.getName());
        assertEquals("cryptoWallet1", cryptoWallet.getUid());
    }

    /**
     * Tests creation of a CryptoWallet with a folder specified.
     */
    @Test
    void withFolderCryptoWalletCreationTest() {
        CryptoWallet cryptoWallet =
                new CryptoWallet("cryptoWallet1", "folder1", "hoseang",
                        "234567uygfde45tghjioiuytre3", "hbhh jh jhy tgjm ktr de sed fe sf");
        assertEquals("folder1", cryptoWallet.getFolderName());
        assertEquals("234567uygfde45tghjioiuytre3", cryptoWallet.getPrivateKey());
        assertEquals("hoseang", cryptoWallet.getUsername());
        assertEquals("hbhh jh jhy tgjm ktr de sed fe sf", cryptoWallet.getSeedPhrase());
        assertEquals("cryptoWallet1", cryptoWallet.getName());
        assertEquals("cryptoWallet1", cryptoWallet.getUid());
    }

    /**
     * Tests editing of CryptoWallet details.
     */
    void editingDetails() {
        CryptoWallet cryptoWallet =
                new CryptoWallet("cryptoWallet1", "folder1", "hoseang",
                        "234567uygfde45tghjioiuytre3", "hbhh jh jhy tgjm ktr de sed fe sf");
        cryptoWallet.setSeedPhrase("fdfghj rewfs weqf weqf weqr fwer weqrgfdvsg thr fdc sda");
        cryptoWallet.setFolderName("folder2");
        cryptoWallet.setName("cryptoWallet2");
        ArrayList<String> urls = new ArrayList<>();
        assertEquals(new ArrayList<String>(), cryptoWallet.getUrls());
        urls.add("http.guefhwbqhyhwuijrf.com");
        urls.add("http.guefhwbqhydbdfdf.com");
        cryptoWallet.setUrls(urls);
        assertEquals(urls, cryptoWallet.getUrls());
        // adding urls
        cryptoWallet.addUrl("http://newsite.com");
        urls.add("http://newsite.com");
        assertEquals(urls, cryptoWallet.getUrls());
        assertEquals(3, cryptoWallet.getUrls().size());
        assertEquals("folder2", cryptoWallet.getFolderName());
        assertEquals("234567uygfde45tghjioiuytre3", cryptoWallet.getPrivateKey());
        assertEquals("hoseang", cryptoWallet.getUsername());
        assertEquals("fdfghj rewfs weqf weqf weqr fwer weqrgfdvsg thr fdc sda",
                cryptoWallet.getSeedPhrase());
        assertEquals("cryptoWallet2", cryptoWallet.getName());
        assertEquals("cryptoWallet2", cryptoWallet.getUid());
    }

    /**
     * Test code to verify the RevealOutput method
     */
    @Test
    void testRevealOutput() {
        CryptoWallet cryptoWallet =
                new CryptoWallet("cryptoWallet1", "folder1", "hoseang",
                        "234567uygfde45tghjioiuytre3", "hbhh jh jhy tgjm ktr de sed fe sf");
        assertEquals("Name: cryptoWallet1\n" +
                "Seed Phrase: hbhh jh jhy tgjm ktr de sed fe sf\n" +
                "Private Key: 234567uygfde45tghjioiuytre3", cryptoWallet.getRevealStr());
        CryptoWallet cryptoWallet2 =
                new CryptoWallet("cryptoWallet1", "hoseang",
                        "234567uygfde45tghjioiuytre3", "hbhh jh jhy tgjm ktr de sed fe sf");
        assertEquals("Name: cryptoWallet1\n" +
                "Seed Phrase: hbhh jh jhy tgjm ktr de sed fe sf\n" +
                "Private Key: 234567uygfde45tghjioiuytre3", cryptoWallet2.getRevealStr());
    }
}
