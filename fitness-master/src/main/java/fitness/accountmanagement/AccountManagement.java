package fitness.accountmanagement;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 * This class represents the management of a fitness account, including user ID, age, fitness goals,
 * and dietary preferences.
 */
public class AccountManagement {

    private String usrId;               // User ID for the account
    private int age;                     // Age of the user
    private String fitnessGoals;         // Fitness goals for the user
    private String dietaryPreferences;   // Dietary preferences of the user
    public static final Type type = new TypeToken<List<AccountManagement>>() {}.getType(); // Type for Gson deserialization

    /**
     * Constructs an AccountManagement object with specified parameters.
     *
     * @param age                The age of the user.
     * @param fitnessGoals       The fitness goals of the user.
     * @param dietaryPreferences The dietary preferences of the user.
     * @param usrId              The user ID associated with the account.
     */
    public AccountManagement(int age, String fitnessGoals, String dietaryPreferences, String usrId) {
        this.usrId = usrId;
        this.age = age;
        this.fitnessGoals = fitnessGoals;
        this.dietaryPreferences = dietaryPreferences;
    }

    /**
     * Gets the type of the list of AccountManagement objects, used for Gson deserialization.
     *
     * @return The type of the list of AccountManagement objects.
     */
    public static Type getType() {
        return type;
    }

    /**
     * Sets the age of the user.
     * If the provided age is not a valid integer, it sets the age to -1 to indicate an invalid value.
     *
     * @param age The age as a string that will be parsed into an integer.
     */
    public void setAge(String age) {
        try {
            this.age = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            this.age = -1; // Invalid age indicator
        }
    }

    /**
     * Sets the fitness goals of the user.
     *
     * @param fitnessGoals The fitness goals to be set for the user.
     */
    public void setFitnessGoals(String fitnessGoals) {
        this.fitnessGoals = fitnessGoals;
    }

    /**
     * Sets the dietary preferences of the user.
     *
     * @param dietaryPreferences The dietary preferences to be set for the user.
     */
    public void setDietaryPreferences(String dietaryPreferences) {
        this.dietaryPreferences = dietaryPreferences;
    }

    /**
     * Gets the age of the user.
     *
     * @return The age of the user.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the fitness goals of the user.
     *
     * @return The fitness goals of the user.
     */
    public String getFitnessGoals() {
        return fitnessGoals;
    }

    /**
     * Gets the dietary preferences of the user.
     *
     * @return The dietary preferences of the user.
     */
    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    /**
     * Gets the user ID associated with the account.
     *
     * @return The user ID of the account.
     */
    public String getUsrId() {
        return usrId;
    }

    /**
     * Sets the user ID for the account.
     *
     * @param usrId The user ID to be set.
     */
    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }
}
