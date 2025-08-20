package fitness.helper;

import fitness.user_management.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class that provides methods to validate user data fields
 * such as email, phone number, password, and general user attributes.
 */
public class Validation {
    public static final String VALID = "Valid";
    private static final String VALID_USER = VALID;

    private Validation() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Matches a given value with a provided regex pattern.
     *
     * @param regex the regex pattern to match against
     * @param value the value to check
     * @return true if the value matches the pattern, otherwise false
     */
    public static boolean regexMatcher(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * Validates that none of the user fields are empty.
     *
     * @param user the user object to check
     * @return an error message if any field is empty, otherwise "Valid"
     */
    private static String emptyUserFieldsTest(User user) {
        if (user.getId().isEmpty()) return "ID can't be empty";
        if (user.getEmail().isEmpty()) return "Email address can't be empty";
        if (user.getPhoneNumber().isEmpty()) return "Phone number can't be empty";
        if (user.getPassword().isEmpty()) return "Password can't be empty";
        if (user.getUsername().isEmpty()) return "Username can't be empty";
        if (user.getFirstName().isEmpty()) return "First name can't be empty";
        if (user.getLastName().isEmpty()) return "Last name can't be empty";
        if (user.getUserType().isEmpty()) return "User type can't be empty";
        return VALID_USER;
    }

    /**
     * Validates the user by checking for empty fields and performing regex checks on email, phone, and password.
     *
     * @param user the user object to validate
     * @return an error message if validation fails, otherwise "Valid"
     */
    public static String userValidationTest(User user) {
        String emptyFieldsTest = emptyUserFieldsTest(user);
        if (!emptyFieldsTest.equals(VALID_USER))
            return emptyFieldsTest;
        if (emailValidationTest(user.getEmail())) {
            if (phoneNumberValidationTest(user.getPhoneNumber())) {
                if (passwordValidationTest(user.getPassword())) {
                    if (user.getUserType().equals("client") || user.getUserType().equals("instructor") )
                        return VALID_USER;
                    else {
                        return "Invalid user type";
                    }
                } else {
                    return "Invalid password";
                }
            }
            return "Invalid phone number";
        }
        return "Invalid email address";
    }

    /**
     * Validates the phone number using a regex pattern for a 10-digit number.
     *
     * @param phoneNumber the phone number to validate
     * @return true if the phone number is valid, otherwise false
     */
    public static boolean phoneNumberValidationTest(String phoneNumber) {
        String regex = "^[0-9]{10}$";
        return regexMatcher(regex, phoneNumber);
    }

    /**
     * Validates the email address using a regex pattern.
     *
     * @param email the email to validate
     * @return true if the email is valid, otherwise false
     */
    public static boolean emailValidationTest(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return regexMatcher(regex, email);
    }

    /**
     * Validates the password using a regex pattern for at least one digit, one lowercase letter,
     * one uppercase letter, and a minimum of 8 characters.
     *
     * @param password the password to validate
     * @return true if the password is valid, otherwise false
     */
    public static boolean passwordValidationTest(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
        return regexMatcher(regex, password);
    }
}
