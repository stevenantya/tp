package seedu.securenus.messages;

/**
 *  The Inquiries class contains constants representing different inquiry messages.
 *  These messages are used to prompt the user for information.
 */
public class InquiryMessages {

    /**
     *  The inquiry message for the user's full name.
     */
    public static final String TEMPLATE_EMPTY = "Your %s cannot be empty!";
    public static final String FULLNAME = "Please enter your Full Name:";
    public static final String FULLNAME_EDIT = "Enter the new Full Name:";

    /**
     * The inquiry message for the credit card number.
     */
    public static final String CREDIT_CARD_NUMBER = "Please enter your Credit Card Number: (e.g. 1234 5678 9012 3456)";
    public static final String CREDIT_CARD_NUMBER_EDIT = "Enter your new Credit Card Number: " +
            "(e.g. 1234 5678 9012 3456)";
    public static final String CREDIT_CARD_NUMBER_RETRY = "Entry failed! Credit Card Number must be of the correct " +
            "format " +
            "(e.g. 1234 5678 9098 7612)";

    /**
     * The inquiry message for the CVC number.
     */
    public static final String CVC_NUMBER = "Please enter your CVC Number:";
    public static final String CVC_NUMBER_EDIT = "Enter the new CVC Number:";
    public static final String CVC_NUMBER_RETRY = "Entry failed! CVC Number must be of the correct format " +
            "(e.g. 123)";
    /**
     * The inquiry message for the expiry date.
     */
    public static final String EXPIRY_DATE = "Please enter your Expiry Date:(MM/YY)";
    public static final String EXPIRY_DATE_EDIT = "Enter the new Expiry Date:(MM/YY)";
    public static final String EXPIRY_DATE_RETRY = "Entry failed! Expiry Date must be of the correct format: (MM/YY)";
    public static final String USERNAME = "Please enter your username:";
    public static final String USERNAME_EDIT = "Enter the new username:";
    public static final String PASSWORD = "Please enter your password:";
    public static final String PASSWORD_EDIT = "Enter the new password:";
    public static final String URL = "Please enter the URL:";
    public static final String URL_EDIT = "Enter the new URL:";
    public static final String NUSNET_ID = "Please enter your NUS Net ID:";
    public static final String NUSNET_ID_EDIT = "Enter the new NUS Net ID:";
    public static final String NUSNET_ID_RETRY = "Entry failed! NUSNet ID must be of the correct NUS format " +
            "(e.g. e0771234)";
    public static final String STUDENT_ID = "Please enter your Student ID:";
    public static final String STUDENT_ID_EDIT = "Enter the new Student ID:";
    public static final String STUDENT_ID_RETRY = "Entry failed! Student ID must be of the correct NUS format " +
            "(e.g. A9876543R)";
    public static final String PRIVATE_KEY = "Please enter your Private Key:";
    public static final String PRIVATE_KEY_EDIT = "Enter the new Private Key:";
    public static final String SEED_PHRASE = "Please enter your Seed Phrase:";
    public static final String SEED_PHRASE_EDIT = "Enter the new Seed Phrase:";
}
