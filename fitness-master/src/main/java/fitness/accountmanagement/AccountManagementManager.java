package fitness.accountmanagement;


import fitness.instructor_interaction.DataBase;
import fitness.user_management.User;

/**
 * Manages the account creation process for fitness users.
 * Provides functionality for adding a user to the system and validating the user input.
 */
public class AccountManagementManager {

    private String status;  // Stores the status of the account management operation
    private static DataBase<AccountManagement> dataBase = new DataBase<>();  // Database instance for storing account information

    /**
     * Adds a new user account to the system after validating the user's details.
     *
     * @param user The AccountManagement object representing the user to be added.
     * @return A message indicating whether the user was successfully added or an error message if validation fails.
     */
    public static String addUser(AccountManagement user) {

        // Validate user details
        if(user.getAge() < 1 || user.getAge() > 120) {
            return "Invalid age";  // Age must be between 1 and 120
        } else if(user.getFitnessGoals().isEmpty()) {
            return "Fitness goals cannot be empty";  // Fitness goals must not be empty
        } else if(user.getDietaryPreferences().isEmpty()) {
            return "Dietary preferences cannot be empty";  // Dietary preferences must not be empty
        } else {
            // Assign user ID and save the account to the database
            user.setUsrId("100" + User.maxId++);
            dataBase.saveObjectToFile("accounts.json", user);  // Save the user profile to the file
            return "Profile was created successfully";  // Success message
        }
    }

    /**
     * Gets the current status of the account management process.
     *
     * @return The status message of the operation.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the account management process.
     *
     * @param status The status message to be set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
