package seedu.securenus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.FolderNotFoundException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.NonExistentFolderException;
import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretMaster;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
// import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test class for the SearchCommand class.
 */
class SearchCommandTest {
    private final Secret mockBasicPassword1;
    {
        mockBasicPassword1 = new BasicPassword("Facebook", "Tom123", "password123", "facebook.com");
    }
    private final Secret mockBasicPassword2;
    {
        mockBasicPassword2 = new BasicPassword("Instagram", "Tom123", "password123", "instagram.com");
    }
    private final Secret mockBasicPasswordWithFolder1;
    {
        mockBasicPasswordWithFolder1 = new BasicPassword("Facebook", "Socials", "Tom123",
                "password123", "fb.com");
    }
    private final Secret mockBasicPasswordWithFolder2;
    {
        mockBasicPasswordWithFolder2 = new BasicPassword("Instagram", "Socials", "Tom123",
                "password123", "ig.com");
    }
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * Sets up the output stream to capture console output during testing.
     */
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    /**
     * Tests the extraction of name from input.
     * The test case checks for extraction of name only.
     *
     * @throws IllegalFolderNameException if folder name is illegal
     * @throws RepeatedIdException if there is a repeated id
     * @throws IllegalSecretNameException if secret name is illegal
     * @throws FolderExistsException if folder exists
     * @throws FolderNotFoundException if folder cannot be found
     */
    @Test
    void extractName_nameOnly() throws IllegalFolderNameException, RepeatedIdException, IllegalSecretNameException,
            FolderExistsException, FolderNotFoundException {
        SecretMaster mockSecureNUSData = new SecretMaster();
        mockSecureNUSData.addSecret(mockBasicPassword1);
        SearchCommand searchCommandNameOnly = new SearchCommand("search Facebook", mockSecureNUSData.getFolders());
        Assertions.assertEquals(searchCommandNameOnly.extractName("search Facebook", "search"), "Facebook");
    }

    /**
     * Tests the extraction of name from input.
     * The test case checks for extraction of name and folder.
     *
     * @throws IllegalFolderNameException if folder name is illegal
     * @throws RepeatedIdException if there is a repeated id
     * @throws IllegalSecretNameException if secret name is illegal
     * @throws FolderExistsException if folder exists
     * @throws FolderNotFoundException if folder cannot be found
     */
    @Test
    void extractName_nameAndFolder() throws IllegalFolderNameException, RepeatedIdException, IllegalSecretNameException,
            FolderExistsException, FolderNotFoundException {
        SecretMaster mockSecureNUSData = new SecretMaster();
        mockSecureNUSData.addSecret(mockBasicPasswordWithFolder1);
        SearchCommand searchCommand = new SearchCommand("search Face f/Socials",
                mockSecureNUSData.getFolders());
        assertEquals(searchCommand.extractName("search Face f/Socials", "search"), "Face");
    }

    /**
     * Tests the execution of search command with a name and folder.
     *
     * @throws IllegalFolderNameException if folder name is illegal
     * @throws RepeatedIdException if there is a repeated id
     * @throws IllegalSecretNameException if secret name is illegal
     * @throws FolderExistsException if folder exists
     * @throws NonExistentFolderException if folder does not exist
     * @throws FolderNotFoundException if folder cannot be found
     */
    @Test
    public void execute_nameAndFolder() throws IllegalFolderNameException,
            RepeatedIdException, IllegalSecretNameException, FolderExistsException, NonExistentFolderException,
            FolderNotFoundException {
        SecretMaster mockSecureNUSData = new SecretMaster();
        mockSecureNUSData.addSecret(mockBasicPasswordWithFolder1);
        mockSecureNUSData.addSecret(mockBasicPasswordWithFolder2);

        SearchCommand command = new SearchCommand("search Facebook f/Socials", mockSecureNUSData.getFolders());
        command.execute(mockSecureNUSData);

        assertEquals("_____________________________________________________\n" +
                        "_____________________________________________________\n" +
                        "Found 1 matches!\n" +
                        "| NO. |          NAME           |      FOLDER       |\n" +
                        "|  1  |        Facebook         |      Socials      |\n" +
                        "_____________________________________________________\n",
                output.toString().replace("\r",""));
    }
}
