/**
 * Provides database-like functionality for managing and persisting data using JSON files.
 */
package fitness.helper;

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fitness.Review;
import fitness.user_management.User;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import fitness.accountmanagement.AccountManagement;

/**
 * Handles CRUD operations for various entities such as Users, Reviews, and Clients.
 * Utilizes JSON files for data persistence.
 */
public class DataBasee {

    /** Gson instance for JSON serialization and deserialization. */
    private static final Gson gson = new Gson();

    /** Default filename for storing user data. */
    public static final String FILENAME = "Users.json";

    /** Status message for the last password operation. */
    public String statuspass;

    private final Logger logger = Logger.getLogger(DataBasee.class.getName());
    /**
     * Saves a new review to the specified file.
     *
     * @param filename The file to save the review.
     * @param newRev   The review to be saved.
     */
    public void saveReviewToFile(String filename, Review newRev) {
        List<Review> allReviews = new ArrayList<>();

        File file = new File(filename);
        if (file.exists()) {
            try (FileReader reader = new FileReader(filename)) {
                Type reviewListType = new TypeToken<List<Review>>() {}.getType();
                allReviews = gson.fromJson(reader, reviewListType);
                if (allReviews == null) {
                    allReviews = new ArrayList<>();
                }
            } catch (IOException e) {
                 logger.severe(e.getMessage());
               
            }
        }

        allReviews.add(newRev);

        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(allReviews, writer);
        } catch (IOException e) {
             logger.severe(e.getMessage());
        }
    }

    /**
     * Loads reviews from the specified file.
     *
     * @param filename The file to load reviews from.
     * @return A list of reviews.
     */
    public List<Review> loadReviewsFromFile(String filename) {
        List<Review> reviews = new ArrayList<>();
        try (FileReader reader = new FileReader(filename)) {
            Type reviewListType = new TypeToken<List<Review>>() {}.getType();
            reviews = gson.fromJson(reader, reviewListType);
        } catch (IOException e) {
             logger.severe(e.getMessage());
        }
        return reviews;
    }

    /**
     * Saves a new user to the specified file.
     *
     * @param filename The file to save the user.
     * @param newUser  The user to be saved.
     */
    public void saveUserToFile(String filename, User newUser) {
        List<User> allUsers = new ArrayList<>();

        File file = new File(filename);
        if (file.exists()) {
            try (FileReader reader = new FileReader(filename)) {
                Type userListType = new TypeToken<List<User>>() {}.getType();
                allUsers = gson.fromJson(reader, userListType);
                if (allUsers == null) {
                    allUsers = new ArrayList<>();
                }
            } catch (IOException e) {
                 logger.severe(e.getMessage());
            }
        }

        allUsers.add(newUser);

        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(allUsers, writer);
        } catch (IOException e) {
             logger.severe(e.getMessage());
        }
    }

    /**
     * Loads users from the specified file.
     *
     * @param filename The file to load users from.
     * @return A list of users.
     */
    public List<User> loadUsersFromFile(String filename) {
        List<User> users = new ArrayList<>();
        try (FileReader reader = new FileReader(filename)) {
            Type userListType = new TypeToken<List<User>>() {}.getType();
            users = gson.fromJson(reader, userListType);
        } catch (IOException e) {
             logger.severe(e.getMessage());
        }
        return users;
    }

    /**
     * Clears the contents of a JSON file by replacing it with an empty string.
     *
     * @param filePath The path to the file to be cleared.
     */
    public void clearJsonFile(String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write("");
        } catch (IOException e) {
             logger.severe(e.getMessage());
        }
    }

    /**
     * Changes the password for a user.
     *
     * @param newPass  The new password to set.
     * @param username The username of the account.
     * @return {@code true} if the password was successfully changed, {@code false} otherwise.
     */
    public boolean changePassword(String newPass, String username) {
        List<User> users = loadUsersFromFile(FILENAME);
        for (User user : users) {
            if (Objects.equals(username, user.getUsername())) {
                user.setPassword(newPass);
                clearJsonFile(FILENAME);
                for (User u : users) {
                    saveUserToFile(FILENAME, u);
                }
                statuspass = "Password changed successfully";
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the active status of a user.
     *
     * @param username The username of the user.
     * @param active   The new active status.
     * @return {@code true} if the status was successfully updated, {@code false} otherwise.
     */
    public boolean updateActive(String username, boolean active) {
        List<User> users = loadUsersFromFile(FILENAME);
        for (User user : users) {
            if (Objects.equals(username, user.getUsername())) {
                user.setActive(active);
                clearJsonFile(FILENAME);
                for (User u : users) {
                    saveUserToFile(FILENAME, u);
                }
                statuspass = "Active status updated successfully";
                return true;
            }
        }
        return false;
    }

    /**
     * Saves a new client to the specified file.
     *
     * @param filename The file to save the client.
     * @param newUser  The client to be saved.
     */
    public void saveClientToFile(String filename, AccountManagement newUser) {
        List<AccountManagement> allUsers = new ArrayList<>();

        File file = new File(filename);
        if (file.exists()) {
            try (FileReader reader = new FileReader(filename)) {
                Type userListType = new TypeToken<List<AccountManagement>>() {}.getType();
                allUsers = gson.fromJson(reader, userListType);
                if (allUsers == null) {
                    allUsers = new ArrayList<>();
                }
            } catch (IOException e) {
                 logger.severe(e.getMessage());
            }
        }

        allUsers.add(newUser);

        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(allUsers, writer);
        } catch (IOException e) {
             logger.severe(e.getMessage());
        }
    }

    /**
     * Loads clients from the specified file.
     *
     * @param filename The file to load clients from.
     * @return A list of clients.
     */
    public List<AccountManagement> loadClientsFromFile(String filename) {
        List<AccountManagement> users = new ArrayList<>();
        try (FileReader reader = new FileReader(filename)) {
            Type userListType = new TypeToken<List<AccountManagement>>() {}.getType();
            users = gson.fromJson(reader, userListType);
        } catch (IOException e) {
             logger.severe(e.getMessage());
        }
        return users;
    }
}
